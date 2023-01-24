package common;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import me.youm.plumblossom.feature.module.ModuleManager;
import me.youm.plumblossom.feature.module.modules.visual.FullBright;
import net.minecraft.client.option.SimpleOption;

import java.util.Optional;


/**
 * @author You_M
 */
public class MySliderCallbacks implements SimpleOption.SliderCallbacks<Double> {
    private final FullBright fullBright = ModuleManager.INSTANCE.getModuleByKClass(FullBright.class);
    @Override
    public Optional<Double> validate(Double double_) {
        return double_>= 0.0 && double_<= (fullBright.getEnabled() ? 10.0 : 1.0) ? Optional.of(double_) : Optional.empty();
    }

    @Override
    public double toSliderProgress(Double double_) {
        return fullBright.getEnabled() ? double_ / 10.0 : double_;
    }
    @Override
    public Double toValue(double sliderProgress) {
        return fullBright.getEnabled() ? sliderProgress * 10.0 : sliderProgress;
    }

    @Override
    public Codec<Double> codec() {
        return Codec.either(
                Codec.doubleRange(
                        0.0,
                        10.0
                ), Codec.BOOL).xmap(either ->
                either.map(
                        value -> value,
                        leftRight  -> leftRight ? (fullBright.getEnabled() ? 10.0 : 1.0) : 0.0
                ), Either::left);
    }
}
