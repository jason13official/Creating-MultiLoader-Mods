package io.github.username.my_first_mod.core.util;

import net.minecraftforge.registries.RegistryObject;

/**
 * In Forge, our implementation of DeferredRegistryObject and usage of its inheritor ForgeDeferredRegistryObject
 * is still straight-forward, but this class is holding a reference to Forge's holder for our registry object.
 * Retrieving the underlying object is still simple, we get the holder and then return the object.
 */
public class ForgeDeferredRegistryObject<T> implements DeferredRegistryObject<T> {

    private final RegistryObject<T> objHolder;

    public ForgeDeferredRegistryObject(RegistryObject<T> objHolder) {
        this.objHolder = objHolder;
    }

    public T get() {
        return this.objHolder.get();
    }
}
