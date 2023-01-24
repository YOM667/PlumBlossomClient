package me.youm.plumblossom.feature.module.modules.visual

import me.youm.plumblossom.feature.module.Module
import me.youm.plumblossom.feature.module.ModuleCategory
import me.youm.plumblossom.feature.module.ModuleSign
import org.lwjgl.glfw.GLFW


/**
 * @author You_M
 */
@ModuleSign(
    name = "fullbright",
    category = ModuleCategory.VISUAL,
    defaultState = false,
    keyBind = GLFW.GLFW_KEY_P,
    description = "the module can improve your gamma value,Fulbright is enabled you can update gamma value in minecraft options"
)
class FullBright : Module(){
    private var oldValue = 0.0
//

    override fun onDisable() {
        this.mc.options.gamma.value = oldValue
    }
//
    override fun onEnable() {
        oldValue = this.mc.options.gamma.value
    }
}