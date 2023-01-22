package me.youm.plumblossom.feature.module.modules.movement

import me.youm.plumblossom.feature.module.Module
import me.youm.plumblossom.feature.module.ModuleCategory
import me.youm.plumblossom.feature.module.ModuleSign

/**
 * @author You_M
 */
@ModuleSign(
    name = "Sprint",
    category = ModuleCategory.MOVEMENT,
    description = "The module can keep you sprinting when you press forward key"
)
class Sprint : Module(){
    var enable : Boolean = false
    init{
        this.toggled = true
    }

}