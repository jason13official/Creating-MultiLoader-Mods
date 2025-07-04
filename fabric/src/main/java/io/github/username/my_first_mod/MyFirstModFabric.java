package io.github.username.my_first_mod;

import net.fabricmc.api.ModInitializer;

public class MyFirstModFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {

        // calling init here calls loadClass in the classes we've defined.
        // since our FabricPlatformHelper is directly registering objects, we're done here.
        MyFirstMod.init();
    }
}
