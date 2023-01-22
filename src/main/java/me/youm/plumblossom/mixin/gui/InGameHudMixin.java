package me.youm.plumblossom.mixin.gui;

import event.EventBus;
import me.youm.plumblossom.feature.event.RenderEvent2D;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author You_M
 */
@Mixin(InGameHud.class)
public class InGameHudMixin extends DrawableHelper {

    @Inject(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/hud/InGameHud;renderStatusEffectOverlay(Lnet/minecraft/client/util/math/MatrixStack;)V",
                    shift = At.Shift.AFTER
            )
    )
    private void render(MatrixStack matrices, float tickDelta, CallbackInfo ci){
        EventBus.INSTANCE.post(new RenderEvent2D(matrices,tickDelta));
    }

}
