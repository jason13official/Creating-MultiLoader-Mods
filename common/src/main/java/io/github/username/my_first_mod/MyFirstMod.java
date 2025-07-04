package io.github.username.my_first_mod;

import io.github.username.my_first_mod.core.registry.MyFirstBlocks;
import io.github.username.my_first_mod.core.registry.MyFirstItems;
import io.github.username.my_first_mod.core.registry.MyFirstTabs;

public class MyFirstMod {

    public static void init() {
        MyFirstBlocks.loadClass();
        MyFirstItems.loadClass();
        MyFirstTabs.loadClass();
    }
}