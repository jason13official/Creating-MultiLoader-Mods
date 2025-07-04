package io.github.username.my_first_mod.mixin;

import io.github.username.my_first_mod.Constants;
import io.github.username.my_first_mod.platform.Services;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class NeoForgeTitleScreenMixin {

    @Inject(at = @At("HEAD"), method = "init()V")
    private void init(CallbackInfo info) {

        if (Services.PLATFORM.isDevelopmentEnvironment()) {
            Constants.LOG.info("This line is printed by a mod mixin from NeoForge!");
            Constants.LOG.info("MC Version: {}", Minecraft.getInstance().getVersionType());
        }
    }
}