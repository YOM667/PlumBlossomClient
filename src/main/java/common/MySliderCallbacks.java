package common;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import net.minecraft.client.option.SimpleOption;

import java.util.Optional;


/**
 * @author You_M
 */
public class MySliderCallbacks<T> implements SimpleOption.SliderCallbacks<Double> {
    @Override
    public Optional<Double> validate(Double double_) {
        return double_ >= 0.0 && double_ <= 10.0 ? Optional.of(double_) : Optional.empty();
    }

    @Override
    public double toSliderProgress(Double double_) {
        return double_ / 10.0;
    }
    @Override
    public Double toValue(double sliderProgress) {
        return sliderProgress * 10.0;
    }

    @Override
    public Codec<Double> codec() {
        return Codec.either(
                Codec.doubleRange(0.0, 10.0), Codec.BOOL).xmap(either ->
                either.map(
                        value -> value, leftRight  -> leftRight ? 10.0 : 0.0
                ), Either::left);
    }
}
