package io.github.username.my_first_mod;


import io.github.username.my_first_mod.core.util.NeoForgeRegistryHelper;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class MyFirstModNeoForge {

    public MyFirstModNeoForge(IEventBus modEventBus) {

        // calling init here calls loadClass in the classes we've defined.
        // however, our ForgePlatformHelper is only adding our objects to Forge's deferred registries
        MyFirstMod.init();

        // here, we are linking our deferred registries to our mod event bus, so that Forge can handle registration for us.
        NeoForgeRegistryHelper.BLOCKS.register(modEventBus);
        NeoForgeRegistryHelper.ITEMS.register(modEventBus);
        NeoForgeRegistryHelper.TABS.register(modEventBus);
    }
}