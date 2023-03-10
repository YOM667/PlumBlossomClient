package me.youm.plumblossom.feature.module.modules.movement

import me.youm.plumblossom.feature.event.handler
import me.youm.plumblossom.feature.event.KeyEvent
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
    var toggled : Boolean = false

    val keyEvent = handler<KeyEvent> {
        if(this.mc.options.sprintKey.isPressed) toggled = !toggled
    }

}