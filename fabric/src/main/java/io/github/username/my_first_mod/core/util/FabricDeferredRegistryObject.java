package io.github.username.my_first_mod.core.util;

/**
 * In Fabric, our implementation of DeferredRegistryObject and usage of its inheritor FabricDeferredRegistryObject
 * is straight-forward, this class is directly holding the underlying registered object.
 */
public class FabricDeferredRegistryObject<T> implements DeferredRegistryObject<T> {

    private final T obj;

    public FabricDeferredRegistryObject(T obj) {
        this.obj = obj;
    }

    public T get() {
        return this.obj;
    }
}
