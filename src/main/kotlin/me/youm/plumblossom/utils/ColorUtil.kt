package me.youm.plumblossom.utils

import java.awt.Color
import kotlin.math.max
import kotlin.math.min

object ColorUtil {
    fun rainbow(speed : Int, index : Int, saturation : Float, brightness : Float, opacity : Float) : Color {
        val angle = ((System.currentTimeMillis() / speed + index) % 360)
        val hue = angle / 360f
        val color = Color(Color.HSBtoRGB(hue, saturation, brightness))
        return Color(color.red, color.green, color.blue, max(0, min(255,  opacity.toInt() * 255)))
    }
}