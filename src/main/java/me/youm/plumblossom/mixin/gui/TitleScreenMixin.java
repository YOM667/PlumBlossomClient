package me.youm.plumblossom.mixin.gui;

import me.youm.plumblossom.PlumBlossom;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author You_M
 */
@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    @Inject(
            at = @At("RETURN"),
            method = "<init>()V"
    )
    private void init(CallbackInfo ci) {
        PlumBlossom.Companion.getLogger().info("welcome to use our client authors {} ",PlumBlossom.Companion.getAUTHORS());
    }
}
