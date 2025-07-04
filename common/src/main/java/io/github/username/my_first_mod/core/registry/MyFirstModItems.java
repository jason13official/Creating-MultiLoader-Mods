package io.github.username.my_first_mod.core.registry;

import io.github.username.my_first_mod.core.util.DeferredRegistryObject;
import io.github.username.my_first_mod.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class MyFirstModItems {

    /**
     * Registering a new Item that can be supplied by our registry object when needed. <br />
     * This initialization references the specific registry that the item is added to.
     */
    public static final DeferredRegistryObject<Item> MY_FIRST_ITEM =
            Services.PLATFORM.register(BuiltInRegistries.ITEM, "my_first_item",
                    () -> new Item(new Item.Properties()));

    /**
     * Registering a new BlockItem that can be supplied by our registry object when needed. <br />
     * The BlockItem also references a block we register by getting it via it's DeferredRegistryObject holder. <br />
     * This initialization defaults to using the Item registry, in the loader-specific implementation of our IPlatformHelper
     */
    public static final DeferredRegistryObject<Item> MY_FIRST_BLOCK_ITEM =
            Services.PLATFORM.registerItem("my_first_block_item",
                    () -> new BlockItem(MyFirstModBlocks.MY_FIRST_BLOCK.get(), new Item.Properties()));

    /**
     * Used for static-initialization of the contents of this class. <br />
     * This method should only be called once during mod initialization.
     */
    public static void loadClass() {}
}
