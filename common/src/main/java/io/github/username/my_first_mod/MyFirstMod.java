package io.github.username.my_first_mod;

import io.github.username.my_first_mod.core.registry.MyFirstModBlocks;
import io.github.username.my_first_mod.core.registry.MyFirstModItems;

public class MyFirstMod {

    public static void init() {
        MyFirstModBlocks.loadClass();
        MyFirstModItems.loadClass();
    }
}