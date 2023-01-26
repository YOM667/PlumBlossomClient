package me.youm.plumblossom.feature.module.modules.player

import me.youm.plumblossom.feature.event.KeyEvent
import me.youm.plumblossom.feature.event.handler
import me.youm.plumblossom.feature.module.Module
import me.youm.plumblossom.feature.module.ModuleCategory
import me.youm.plumblossom.feature.module.ModuleSign
import org.lwjgl.glfw.GLFW

/**
 * @author You_M
 */
@ModuleSign(
    name = "freelook",
    category = ModuleCategory.PLAYER,
    description = "the module can rotate your camera but your player not rotate toward",
    defaultState = false,
    keyBind = GLFW.GLFW_KEY_L
)
class Freelook : Module() {

    var freelook = false

    val key = handler<KeyEvent> {event->
        if (event.key.code == GLFW.GLFW_KEY_V) {
            freelook = when (event.action) {
                GLFW.GLFW_PRESS -> true
                GLFW.GLFW_RELEASE -> false
                else -> false
            }
        }
    }

}