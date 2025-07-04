package io.github.username.my_first_mod.core.util;

import io.github.username.my_first_mod.Constants;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Forge patches in their own registry wrappers, which we must utilize to ensure our objects are registered at the correct time and in the correct order. <br />
 * This class simply provides a method to retrieve the appropriate registry, which should be attached to the IEventBus for our mod
 * separately in the mod initializer.
 */
public class ForgeRegistryHelper {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);
    public static final DeferredRegister<Block> BLOCK = DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.MOD_ID);

    @SuppressWarnings("unchecked")
    public static <T> DeferredRegister<T> deferredRegisterFor(Registry<T> objRegistry) {

        if (objRegistry.key().location() == ForgeRegistries.Keys.ITEMS.location()) return (DeferredRegister<T>) ITEMS;
        else if (objRegistry.key().location() == ForgeRegistries.Keys.BLOCKS.location()) return (DeferredRegister<T>) BLOCK;

        throw new IllegalArgumentException("No registry linked in Forge module to register type: " + objRegistry.key());
        // return null; // throws an error if registering to undefined/unlinked Forge registry
    }
}

