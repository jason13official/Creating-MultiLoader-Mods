package io.github.username.my_first_mod.core.registry;

import io.github.username.my_first_mod.core.util.DeferredRegistryObject;
import io.github.username.my_first_mod.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class MyFirstTabs {

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
