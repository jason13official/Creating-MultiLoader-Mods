package io.github.username.my_first_mod.core.registry;

import io.github.username.my_first_mod.core.util.DeferredRegistryObject;
import io.github.username.my_first_mod.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

/**
 * Not everything can be registered in a simple fashion, sometimes mod loaders will change or patch portions of
 * vanilla code to give modders a better development experience. However, we don't receive those benefits
 * unless we can directly request them via platform helpers. <br />
 */
public class MyFirstTabs {

    /**
     * Here's a classic example; CreativeModeTabs are typically built with a {@link CreativeModeTab.Row} and an int value for their column index. <br />
     * We don't want to arbitrarily define these values, so we use an additional platform helper during initialization to get the modified tab builder.
     */
    public static final DeferredRegistryObject<CreativeModeTab> MY_FIRST_TAB =
            Services.PLATFORM.register(BuiltInRegistries.CREATIVE_MODE_TAB, "my_first_tab",
                    () -> Services.PLATFORM.tabBuilder()
                            .icon(() -> new ItemStack(Items.STICK))
                            .title(Component.literal("My First Tab"))
                            .displayItems((itemDisplayParameters, output) -> {
                                output.accept(MyFirstItems.MY_FIRST_ITEM.get());
                                output.accept(MyFirstItems.MY_FIRST_BLOCK_ITEM.get());
                            }).build());

    public static void loadClass() {}
}
