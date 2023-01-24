package me.youm.plumblossom.mixin.client;

import common.MySliderCallbacks;
import me.youm.plumblossom.feature.module.ModuleManager;
import me.youm.plumblossom.feature.module.modules.visual.FullBright;
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

    private final FullBright fullBright = ModuleManager.INSTANCE.getModuleByKClass(FullBright.class);
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

            if (i == 0) return GameOptions.getGenericValueText(optionText, Text.translatable("options.gamma.min"));

            if(fullBright.getEnabled()){
                if (i == 500) return GameOptions.getGenericValueText(optionText, Text.translatable("options.gamma.default"));
                if (i == 1000) return GameOptions.getGenericValueText(optionText, Text.translatable("options.gamma.max"));
            }
            if (i == 50) return GameOptions.getGenericValueText(optionText, Text.translatable("options.gamma.default"));
            if(i == 100) return GameOptions.getGenericValueText(optionText, Text.translatable("options.gamma.max"));

            return GameOptions.getGenericValueText(optionText, i);
        }, new MySliderCallbacks(), 1.0, value -> {});
    }
}
