package me.youm.plumblossom.feature.setting

/**
 * @author You_M
 */
data class BoolSetting(override var value: Boolean, override val label: String) : Setting<Boolean>(value, label)
data class NumberSetting<T : Number>(override var value: T, val max: T, val min: T, override val label : String) : Setting<T>(value,label)
data class ModeSetting<T : Enum<*>>(override var value : T, val mode : T, override val label: String) : Setting<T>(value, label)