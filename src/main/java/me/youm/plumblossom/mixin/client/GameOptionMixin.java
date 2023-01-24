package me.youm.plumblossom.mixin.client;

import common.MySliderCallbacks;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * @author You_M
 */
@Mixin(GameOptions.class)
public class GameOptionMixin {
    @Shadow
    @Final
    @Mutable
    private SimpleOption<Double> gamma;

    @Redirect(
            method = "<init>",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/option/GameOptions;gamma:Lnet/minecraft/client/option/SimpleOption;"
            )
    )
    private void init(GameOptions instance, SimpleOption<Double> simpleOption){
        gamma = new SimpleOption<>("options.gamma", SimpleOption.emptyTooltip(), (optionText, value) -> {
            int i = (int)(value * 100.0);
            if (i == 0) {
                return GameOptions.getGenericValueText(optionText, Text.translatable("options.gamma.min"));
            }
            if (i == 50) {
                return GameOptions.getGenericValueText(optionText, Text.translatable("options.gamma.default"));
            }
            if (i == 1000) {
                return GameOptions.getGenericValueText(optionText, Text.translatable("options.gamma.max"));
            }
            return GameOptions.getGenericValueText(optionText, i);
        }, new MySliderCallbacks<>(), 0.5, value -> {});
    }
}
