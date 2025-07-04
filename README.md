This gist serves as a non-exhaustive, high-level overview of implementing [MultiLoader-Template](https://github.com/jaredlll08/MultiLoader-Template),
which was created by jaredlll08 and contributors in 2021 for [Hacktoberfest](https://github.com/topics/hacktoberfest).

> Trivia: Specifically, the first commit to MultiLoader-Template was [a Markdown file simply reading "FabricForgeTogether"](https://github.com/jaredlll08/MultiLoader-Template/commit/e5e55290ea2795de293ac7c698592bc79a7635a7),
> which was committed by jaredlll08 on Jul 23, 2021!

Only official mojang mappings are used in this gist.

We are targeting 1.20.1 as the Minecraft version of our mod.
---

### Development Environment Details

- JetBrain's IDE, [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download)
- Eclipse Adoptium's open-source JDK,  [*Temurin*](https://adoptium.net/temurin/releases)

You can begin by cloning, forking, or directly downloading the [MultiLoader-Template](https://github.com/jaredlll08/MultiLoader-Template) repository.

Optionally, [GitKraken Desktop](https://www.gitkraken.com/git-client) is helpful for working with git worktrees. You can also use GitHub Desktop to clone the repository and push changes to a repository easily.
In either case, make sure the `rootProject.name` property in `settings.gradle` matches the name of the root folder.

<!-- Can someone add info about usng the publishing plugins?? -->

<!-- [**Forgix**](https://github.com/PacifistMC/Forgix) is used at the end of our project to combine our Fabric and (Neo)Forge files into a single merged jar.-->

---

### YOU ARE HERE: Java is installed, IntelliJ IDEA is open to your clone of MultiLoader-Template

For the purposes of this tutorial, we are using the fully-qualified namespace `io.github.username.my_first_mod`, and the mod ID of `my_first_mod`.

To begin, we must refactor the mod (tip: SHIFT+F6 to refactor a top-level class name will rename the file as well). This is the practice of replacing every instance of `com.example.examplemod` with our preferred namespace, and the same with any instance of `examplemod` to use our mod ID. Make sure to update mixin files as well as fabric.mod.json and Constants.java

It's also suggested to rename the entrypoint classes, instead of `CommonClass` and `ExampleMod` in both the fabric and forge module, let's rename them respectively to
MyFirstMod, MyFirstModFabric, and MyFirstModForge. You can/should follow this pattern for most files. Now we have:

- `MyFirstMod`, in the `common` module, which is invoked by our mod-loader entrypoints via `MyFirstMod#init()`
- `MyFirstModFabric`, in the `fabric` module, which implements `ModInitializer` and it linked in our fabric.mod.json file as an entrypoint
- `MyFirstModForge`, in the `forge` module, which is annotated with `@Mod` and contains a parameterless constructor as our Forge entrypoint.

> Tip: To ensure that you don't miss replacing any occurence of a string in your project, double-tap SHIFT to bring up a search bar

---

### Registering objects to Minecraft's registries

*Preface: Fabric (Neo)Forge delay/defer registration of objects to Minecraft's built-in registries to ensure that objects are registered at the right time and in the correct order. 
In practice, this means we want to define Supplier-like objects with a reference to the underlying object being registered.
There are multiple libraries that can handle this functionality for us, such as [RegistrationUtils](https://github.com/Matyrobbrt/RegistrationUtils) and [Bookshelf](https://github.com/Darkhax-Minecraft/Bookshelf),
but we are going to implement a barebones system described by [Silk](https://github.com/TheSilkMiner). over a Discord chat.*

In our `common` module, we will create a class with the fully-qualified class path of `io.github.username.my_first_mod.core.util.DeferredRegistryObject` as follows:
```Java
public interface DeferredRegistryObject<T> extends Supplier<T> {
    
    T get(); // not required, provided by extending Supplier
}
```

This interface allows us to create custom implementations of our registry object for each mod loader,
which we will create in our `fabric` and `forge` modules with the class paths of:

- io.github.username.my_first_mod.core.util.FabricDeferredRegistryObject;
- io.github.username.my_first_mod.core.util.ForgeDeferredRegistryObject;


```Java
public class FabricDeferredRegistryObject<T> implements DeferredRegistryObject<T> {

    private final T obj;

    public FabricDeferredRegistryObject(T obj) {
        this.obj = obj;
    }

    public T get() {
        return this.obj;
    }
}
```

```Java
import net.minecraftforge.registries.RegistryObject;

public class ForgeDeferredRegistryObject<T> implements DeferredRegistryObject<T> {

    private final RegistryObject<T> objHolder;

    public ForgeDeferredRegistryObject(RegistryObject<T> objHolder) {
        this.objHolder = objHolder;
    }

    public T get() {
        return this.objHolder.get();
    }
}
```

*Note how the Forge implementation is directly holding another holder, rather than the underlying registered object.
In practice, we will still use the DeferredRegistryObject in the same fashion.*

Creation of new DeferredRegistryObjects will be handled via platform helpers, with Fabric being straight-forward and Forge having a small caveat.

In `common`, we'll add the following method to our IPlatformHelper interface:
```Java
<T, U extends T> DeferredRegistryObject<U> register(Registry<T> objRegistry, String objName, Supplier<U> objSupplier);
```

With the following implementations in Fabric and Forge:
```Java
public <T, U extends T> DeferredRegistryObject<U> register(Registry<T> objRegistry, String objName, Supplier<U> objSupplier) {
    return new FabricDeferredRegistryObject<>(Registry.register(objRegistry, new ResourceLocation(Constants.MOD_ID), objSupplier.get()));
}
```
```Java
public <T, U extends T> DeferredRegistryObject<U> register(Registry<T> objRegistry, String objName, Supplier<U> objSupplier) {
    DeferredRegister<T> registry = ForgeRegistryHelper.deferredRegisterFor(objRegistry);
    return new ForgeDeferredRegistryObject<>(registry.register(objName, objSupplier));
}
```

Note the usage of `ForgeRegistryHelper`, which is a custom class for retrieving the proper mod registry:
```Java
public class ForgeRegistryHelper {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);
    public static final DeferredRegister<Block> BLOCK = DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.MOD_ID);

    @SuppressWarnings("unchecked")
    public static <T> DeferredRegister<T> deferredRegisterFor(Registry<T> objRegistry) {

        if (objRegistry.key().location() == ForgeRegistries.Keys.ITEMS.location()) return (DeferredRegister<T>) ITEMS;
        else if (objRegistry.key().location() == ForgeRegistries.Keys.BLOCKS.location()) return (DeferredRegister<T>) BLOCK;

        throw new IllegalArgumentException("No registry linked in Forge module to register type: " + objRegistry.key());
        // return null; // throws an error if registering to undefined/unlinked Forge registry
    }
}
```

Now we are set up to register blocks and items to Minecraft's built-in registries using each mod-loaders preferred method.
Here's an example of a class that defines and registers our first item:
```Java
public class MyFirstItems {

    public static final DeferredRegistryObject<Item> MY_FIRST_ITEM =
            Services.PLATFORM.register(BuiltInRegistries.ITEM, "my_first_item",
                    () -> new Item(new Item.Properties()));

    public static void loadClass() {}
}
```

In Fabric, our items will be registered as long as we call loadClass during mod initialization.
But in Forge, we need to make sure we link our registries to the mod event bus provided by Forge.

Assuming that you are calling `MyFirstItems#loadClass` within `MyFirstMod#init`:
```Java
@Mod(Constants.MOD_ID)
public class MyFirstModForge {
    
    public MyFirstModForge() {

        // calling init here calls loadClass in the classes we've defined.
        // however, our ForgePlatformHelper is only adding our objects to Forge's deferred registries
        MyFirstMod.init();

        // here, we are linking our deferred registries to our mod event bus, so that Forge can handle registration for us.
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ForgeRegistryHelper.BLOCK.register(modEventBus);
        ForgeRegistryHelper.ITEMS.register(modEventBus);
    }
}
```
