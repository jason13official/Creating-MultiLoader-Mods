package io.github.username.my_first_mod.platform;

import io.github.username.my_first_mod.core.util.DeferredRegistryObject;
import io.github.username.my_first_mod.core.util.ForgeDeferredRegistryObject;
import io.github.username.my_first_mod.core.util.ForgeRegistryHelper;
import io.github.username.my_first_mod.platform.services.IPlatformHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }

    @Override
    public <T, U extends T> DeferredRegistryObject<U> register(Registry<T> objRegistry, String objName, Supplier<U> objSupplier) {
        DeferredRegister<T> registry = ForgeRegistryHelper.deferredRegisterFor(objRegistry);
        return new ForgeDeferredRegistryObject<>(registry.register(objName, objSupplier));
    }

    @Override @SuppressWarnings("unchecked")
    public <T, U extends T> DeferredRegistryObject<U> registerItem(String objName, Supplier<U> objSupplier) {
        return this.<T, U>register((Registry<T>) ForgeRegistries.ITEMS, objName, objSupplier); // todo can we/should we use BuiltInRegistries.ITEM anyway?
    }

    @Override
    public CreativeModeTab.Builder tabBuilder() {
        return CreativeModeTab.builder();
    }
}