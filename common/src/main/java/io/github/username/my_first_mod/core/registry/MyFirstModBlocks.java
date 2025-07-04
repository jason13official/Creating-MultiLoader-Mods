package io.github.username.my_first_mod.core.registry;

import io.github.username.my_first_mod.core.util.DeferredRegistryObject;
import io.github.username.my_first_mod.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class MyFirstModBlocks {

    /**
     * Registering a new Block that can be supplied by our registry object when needed. <br />
     * This initialization references the specific registry that the block is added to.
     */
    public static final DeferredRegistryObject<Block> MY_FIRST_BLOCK =
            Services.PLATFORM.register(BuiltInRegistries.BLOCK, "my_first_block",
                    () -> new Block(BlockBehaviour.Properties.of()));

    /**
     * Used for static-initialization of the contents of this class. <br />
     * This method should only be called once during mod initialization.
     */
    public static void loadClass() {}
}
