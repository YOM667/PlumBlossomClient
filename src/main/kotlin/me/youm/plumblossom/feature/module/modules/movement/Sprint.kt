package me.youm.plumblossom.feature.module.modules.movement

import event.handler
import me.youm.plumblossom.feature.event.KeyEvent
import me.youm.plumblossom.feature.event.RenderEvent2D
import me.youm.plumblossom.feature.module.Module
import me.youm.plumblossom.feature.module.ModuleCategory
import me.youm.plumblossom.feature.module.ModuleSign

/**
 * @author You_M
 */
@ModuleSign(
    name = "Sprint",
    category = ModuleCategory.MOVEMENT,
    description = "The module can keep you sprinting when you press forward key",
    defaultState = true
)
class Sprint : Module(){
    var enable : Boolean = false
    val keyEvent = handler<KeyEvent> {
        if(this.mc.options.sprintKey.isPressed) enable = !enable
    }
    val render = handler<RenderEvent2D> { event->
        if(this.mc.player?.isSprinting!!) {
            this.mc.textRenderer.draw(event.matrices,if(enable)"Sprint Toggle" else "Sprint Vanilla",10f,100f,-1)
        }
    }
}