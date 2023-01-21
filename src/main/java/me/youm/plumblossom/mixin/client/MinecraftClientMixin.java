package me.youm.plumblossom.mixin.client;

import event.EventBus;
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
        EventBus.INSTANCE.post(new GameTickUpdateEvent());
    }
}
