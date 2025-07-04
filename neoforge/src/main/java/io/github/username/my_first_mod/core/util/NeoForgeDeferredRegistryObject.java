package io.github.username.my_first_mod.core.util;

import net.neoforged.neoforge.registries.DeferredHolder;

/**
 * In Forge, our implementation of DeferredRegistryObject and usage of its inheritor ForgeDeferredRegistryObject
 * is still straight-forward, but this class is holding a reference to Forge's holder for our registry object.
 * Retrieving the underlying object is still simple, we get the holder and then return the object.
 */
public class NeoForgeDeferredRegistryObject<T> implements DeferredRegistryObject<T> {

    private final DeferredHolder<? super T, T> objHolder;

    public NeoForgeDeferredRegistryObject(DeferredHolder<? super T, T> objHolder) {
        this.objHolder = objHolder;
    }

    public T get() {
        return this.objHolder.get();
    }
}
