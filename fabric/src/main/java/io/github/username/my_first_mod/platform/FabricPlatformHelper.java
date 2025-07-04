package io.github.username.my_first_mod.platform;

import io.github.username.my_first_mod.Constants;
import io.github.username.my_first_mod.core.util.DeferredRegistryObject;
import io.github.username.my_first_mod.core.util.FabricDeferredRegistryObject;
import io.github.username.my_first_mod.platform.services.IPlatformHelper;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

import java.util.function.Supplier;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public <T, U extends T> DeferredRegistryObject<U> register(Registry<T> objRegistry, String objName, Supplier<U> objSupplier) {
        return new FabricDeferredRegistryObject<>(Registry.register(objRegistry, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, objName), objSupplier.get()));
    }

    @Override @SuppressWarnings("unchecked")
    public <T, U extends T> DeferredRegistryObject<U> registerItem(String objName, Supplier<U> objSupplier) {
        return this.<T, U>register((Registry<T>) BuiltInRegistries.ITEM, objName, objSupplier);
    }

    @Override
    public CreativeModeTab.Builder tabBuilder() {
        return FabricItemGroup.builder();
    }
}
