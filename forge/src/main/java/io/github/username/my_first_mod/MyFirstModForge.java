package io.github.username.my_first_mod;

import io.github.username.my_first_mod.core.util.ForgeRegistryHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MOD_ID)
public class MyFirstModForge {
    
    public MyFirstModForge() {

        // calling init here calls loadClass in the classes we've defined.
        // however, our ForgePlatformHelper is only adding our objects to Forge's deferred registries
        MyFirstMod.init();

        // here, we are linking our deferred registries to our mod event bus, so that Forge can handle registration for us.
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ForgeRegistryHelper.BLOCKS.register(modEventBus);
        ForgeRegistryHelper.ITEMS.register(modEventBus);
        ForgeRegistryHelper.TABS.register(modEventBus);
    }
}