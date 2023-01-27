package me.youm.plumblossom.ui.clickgui.classic

import me.youm.plumblossom.ui.clickgui.classic.component.IComponent
import me.youm.plumblossom.utils.render.RenderUtil
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.Text
import java.awt.Color

/**
 * @author You_M
 */
class ClassicScreen : Screen(Text.translatable("classic")), IComponent {

    companion object {
        const val screenHeight = 230
        const val screenWidth = 400
        const val navWidth = 80
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
        super.render(matrices!!, mouseX, mouseY, delta)
        val xPos = (this.width / 2) - (screenWidth / 2)
        val yPos = (this.height / 2) - (screenHeight / 2)

        /* render background */
        RenderUtil.drawRect(matrices, xPos, yPos, screenWidth, screenHeight, Color(240, 240, 240))
        /* render navbar */
        RenderUtil.drawRect(matrices, xPos, yPos, navWidth, screenHeight, Color(220, 220, 220))
        /* render close button */
        if (isHovered(xPos + screenWidth - 35, yPos, 35, 15, mouseX, mouseY)) {
            RenderUtil.drawRect(matrices, xPos + screenWidth - 35, yPos, 35, 15, Color(255, 75, 75))
        }
    }

}