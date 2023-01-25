package me.youm.plumblossom.feature.module.modules.visual

import me.youm.plumblossom.feature.module.Module
import me.youm.plumblossom.feature.module.ModuleCategory
import me.youm.plumblossom.feature.module.ModuleSign
import me.youm.plumblossom.ui.clickgui.classic.ClassicScreen
import org.lwjgl.glfw.GLFW

/**
 * @author You_M
 */
@ModuleSign(
    name = "ClickGUI",
    category = ModuleCategory.VISUAL,
    keyBind = GLFW.GLFW_KEY_RIGHT_SHIFT,
    description = "the module can open a screen to update others module setting",
    defaultState = false
)
class ClickGUI : Module() {
    private val classicScreen = ClassicScreen()
    override fun onEnable() {
        this.mc.setScreen(classicScreen)
    }

    override fun onDisable() {
        this.mc.setScreen(null)
    }
}