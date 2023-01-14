package me.youm.plumblossom.mixin.gui.client;

import me.youm.plumblossom.feature.event.EventManager;
import me.youm.plumblossom.feature.event.GameTickUpdateEvent;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author You_M
 */
@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Inject(
            method = "tick",
            at = @At("HEAD")
    )
    private void tickEvent(CallbackInfo ci){
        EventManager.INSTANCE.callEvent(new GameTickUpdateEvent());
    }
}
