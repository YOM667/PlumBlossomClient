package me.youm.plumblossom.mixin.client;

import com.mojang.authlib.GameProfile;
import me.youm.plumblossom.feature.module.ModuleManager;
import me.youm.plumblossom.feature.module.modules.movement.Sprint;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * @author You_M
 */
@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin extends AbstractClientPlayerEntity {


    public ClientPlayerEntityMixin(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }
    private final Sprint sprint = ModuleManager.INSTANCE.getModuleByKClass(Sprint.class);
    @Redirect(
            method = "tickMovement",
            at = @At(
                    target = "Lnet/minecraft/client/option/KeyBinding;isPressed()Z",
                    value = "INVOKE"
            )
    )
    private boolean isSprinting(KeyBinding instance){
        return sprint.getToggled();
    }
}
