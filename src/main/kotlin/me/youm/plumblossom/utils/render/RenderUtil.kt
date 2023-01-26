package me.youm.plumblossom.utils.render

import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.gui.DrawableHelper
import net.minecraft.client.render.*
import net.minecraft.client.util.math.MatrixStack
import org.joml.Matrix4f
import java.awt.Color

object RenderUtil : DrawableHelper() {
    /**
     * render rectangle
     */
    fun <T : Number> drawRect(matrices: MatrixStack, x: T, y: T, width: T, height: T, color: Color) {
        fill(matrices, x.toInt(), y.toInt(), x.toInt() + width.toInt(), y.toInt() + height.toInt(), color.rgb)
    }

    //----------------------------------------------------------------------
    /**
     * render column gradient rectangle
     */
    fun <T : Number> drawColumnGradientRect(matrices: MatrixStack, x: T, y: T, width: T, height: T, startColor: Color, endColor: Color){
        drawColumnGradientRect(matrices, x.toInt(), y.toInt(),x.toInt() + width.toInt(),y.toInt() + height.toInt(), startColor, endColor,this.zOffset)
    }
    private fun drawColumnGradientRect(matrices: MatrixStack, startX: Int, startY: Int, endX: Int, endY: Int, colorStart: Color, colorEnd: Color, z: Int) {
        RenderSystem.disableTexture()
        RenderSystem.enableBlend()
        RenderSystem.defaultBlendFunc()
        RenderSystem.setShader { GameRenderer.getPositionColorProgram() }
        val tessellator = Tessellator.getInstance()
        val bufferBuilder = tessellator.buffer
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR)
        drawColumnGradientRect(matrices.peek().positionMatrix, bufferBuilder, startX, startY, endX, endY, z, colorStart, colorEnd)
        tessellator.draw()
        RenderSystem.disableBlend()
        RenderSystem.enableTexture()
    }

    private fun drawColumnGradientRect(matrix: Matrix4f?, builder: BufferBuilder, startX: Int, startY: Int, endX: Int, endY: Int, z: Int, colorStart: Color, colorEnd: Color) {
        val startAlpha = colorStart.alpha / 255.0f
        val startRed = colorStart.red / 255.0f
        val startGreen = colorStart.green / 255.0f
        val startBlue = colorStart.blue / 255.0f
        val endAlpha = colorEnd.alpha / 255.0f
        val endRed = colorEnd.red / 255.0f
        val endGreen = colorEnd.green / 255.0f
        val endBlue = colorEnd.blue / 255.0f
        /* top left corner */
        builder.vertex(matrix, startX.toFloat(), startY.toFloat(), z.toFloat()).color(startRed, startGreen, startBlue, startAlpha).next()
        /* bottom left corner */
        builder.vertex(matrix, startX.toFloat(), endY.toFloat(), z.toFloat()).color(startRed, startGreen, startBlue, startAlpha).next()
        /* bottom right corner */
        builder.vertex(matrix, endX.toFloat(), endY.toFloat(), z.toFloat()).color(endRed, endGreen, endBlue, endAlpha).next()
        /* top right corner */
        builder.vertex(matrix, endX.toFloat(), startY.toFloat(), z.toFloat()).color(endRed, endGreen, endBlue, endAlpha).next()
    }
    //----------------------------------------------------------------------
}
