package me.youm.plumblossom.mixin.gui.client;

import event.EventBus;
import me.youm.plumblossom.feature.event.KeyEvent;
import net.minecraft.client.Keyboard;
import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author You_M
 */
@Mixin(Keyboard.class)
public class KeyboardMixin {
    @Inject(method = "onKey", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/InputUtil;fromKeyCode(II)Lnet/minecraft/client/util/InputUtil$Key;", shift = At.Shift.AFTER))
    private void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        EventBus.INSTANCE.post(new KeyEvent(InputUtil.fromKeyCode(key, scancode), action, modifiers));
    }
}
