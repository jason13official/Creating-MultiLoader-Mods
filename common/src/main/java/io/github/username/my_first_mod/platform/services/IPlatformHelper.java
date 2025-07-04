package io.github.username.my_first_mod.platform.services;

import io.github.username.my_first_mod.core.util.DeferredRegistryObject;
import net.minecraft.core.Registry;

import java.util.function.Supplier;

public interface IPlatformHelper {

    /**
     * Gets the name of the current platform
     *
     * @return The name of the current platform.
     */
    String getPlatformName();

    /**
     * Checks if a mod with the given id is loaded.
     *
     * @param modId The mod to check if it is loaded.
     * @return True if the mod is loaded, false otherwise.
     */
    boolean isModLoaded(String modId);

    /**
     * Check if the game is currently in a development environment.
     *
     * @return True if in a development environment, false otherwise.
     */
    boolean isDevelopmentEnvironment();

    /**
     * Gets the name of the environment type as a string.
     *
     * @return The name of the environment type.
     */
    default String getEnvironmentName() {

        return isDevelopmentEnvironment() ? "development" : "production";
    }

    /**
     * Implementations should add objects to their appropriate registries, either by directly registering to the objRegistry parameter in Fabric,
     * or getting the mod-linked registry from Forge using objRegistry's value.
     */
    <T, U extends T> DeferredRegistryObject<U> register(Registry<T> objRegistry, String objName, Supplier<U> objSupplier);

    /**
     * Implementations should utilize {@link IPlatformHelper#register(Registry, String, Supplier)} with reference to {@link net.minecraft.core.registries.BuiltInRegistries#ITEM}
     */
    <T, U extends T> DeferredRegistryObject<U> registerItem(String objName, Supplier<U> objSupplier);
}