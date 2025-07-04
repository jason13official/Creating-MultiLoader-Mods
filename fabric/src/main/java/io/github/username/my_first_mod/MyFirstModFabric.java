package io.github.username.my_first_mod;

import net.fabricmc.api.ModInitializer;

public class MyFirstModFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {

        // calling init here calls loadClass in the classes we've defined.
        // Our FabricPlatformHelper is directly registering objects.
        MyFirstMod.init();
    }
}
