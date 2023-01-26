package me.youm.plumblossom.utils.render

import net.minecraft.client.gui.DrawableHelper
import net.minecraft.client.util.math.MatrixStack
import java.awt.Color

object RenderUtil {
    fun drawRect(matrices: MatrixStack, x: Int, y: Int, width: Int, height: Int, color: Color) {
        DrawableHelper.fill(matrices, x, y, x + width, y + height, color.rgb)
    }
    fun <T : Number> drawRect(matrices: MatrixStack, x: T, y: T, width: T, height: T, rgb: Int) {
        DrawableHelper.fill(matrices, x.toInt(), y.toInt(), x.toInt() + width.toInt(), y.toInt() + height.toInt(), rgb)
    }
}
