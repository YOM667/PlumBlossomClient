package me.youm.plumblossom.feature.module.modules.movement

import me.youm.plumblossom.feature.event.GameTickUpdateEvent
import me.youm.plumblossom.feature.event.handler
import me.youm.plumblossom.feature.module.Module
import me.youm.plumblossom.feature.module.ModuleCategory
import me.youm.plumblossom.feature.module.ModuleSign
import org.lwjgl.glfw.GLFW

/**
 * @author You_M
 */
@ModuleSign(
    name = "Sprint",
    category = ModuleCategory.MOVEMENT,
    keyBind = GLFW.GLFW_KEY_P,
    description = "The module can keep you sprinting when you press forward key"
)
class Sprint : Module(){
    override fun onEnable() {
        this.mc.player?.isSprinting = true
        println("sb")
    }

    override fun onDisable() {
        this.mc.player?.isSprinting = false

    }
    val update = handler<GameTickUpdateEvent> {
        this.mc.player?.isSprinting = true
    }
}