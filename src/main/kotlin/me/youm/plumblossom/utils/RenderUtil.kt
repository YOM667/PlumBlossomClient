package me.youm.plumblossom.utils

import net.minecraft.client.gui.DrawableHelper
import net.minecraft.client.util.math.MatrixStack
import java.awt.Color

object RenderUtil {
    fun drawRect(matrices: MatrixStack, x: Int, y: Int, width: Int, height: Int, color: Color) {
        DrawableHelper.fill(matrices, x, y, x + width, y + height, color.rgb)
    }

    fun drawRect(matrices: MatrixStack, x: Int, y: Int, width: Int, height: Int, rgb: Int) {
        DrawableHelper.fill(matrices, x, y, x + width, y + height, rgb)
    }
}