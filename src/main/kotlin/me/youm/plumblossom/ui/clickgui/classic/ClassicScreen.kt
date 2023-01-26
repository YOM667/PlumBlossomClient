package me.youm.plumblossom.ui.clickgui.classic

import me.youm.plumblossom.utils.render.RenderUtil
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.Text
import java.awt.Color

/**
 * @author You_M
 */
class ClassicScreen : Screen(Text.translatable("classic")) {

    companion object {
        const val screenHeight: Int = 200
        const val screenWidth: Int = 350
    }

    override fun init() {
        super.init()
    }

    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        return super.mouseClicked(mouseX, mouseY, button)
    }

    override fun keyPressed(keyCode: Int, scanCode: Int, modifiers: Int): Boolean {
        return super.keyPressed(keyCode, scanCode, modifiers)
    }

    override fun render(matrices: MatrixStack?, mouseX: Int, mouseY: Int, delta: Float) {
        super.render(matrices, mouseX, mouseY, delta)
        val xPos = (this.width / 2) - (screenWidth / 2)
        val yPos = (this.height / 2) - (screenHeight / 2)
        RenderUtil.drawRect(matrices!!,xPos, yPos, xPos + screenWidth, yPos + screenHeight, Color(185, 185, 185))
    }

}