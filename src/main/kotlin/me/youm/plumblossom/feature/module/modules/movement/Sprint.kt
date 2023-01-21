package me.youm.plumblossom.feature.module.modules.movement

import event.handler
import me.youm.plumblossom.feature.event.GameTickUpdateEvent
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
    init{
        this.toggled = false
    }
    val update = handler<GameTickUpdateEvent> {
        val options = this.mc.options
        if(options.forwardKey.isPressed){
            if(!this.mc.player!!.isSubmergedInWater.
                and(!this.mc.player!!.isInsideWaterOrBubbleColumn).
                and(!this.mc.player!!.isTouchingWater)) this.mc.player?.isSprinting = true
        }
    }
}