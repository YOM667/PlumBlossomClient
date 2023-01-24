package me.youm.plumblossom.feature.module.modules.visual

import me.youm.plumblossom.feature.event.RenderEvent2D
import me.youm.plumblossom.feature.event.handler
import me.youm.plumblossom.feature.module.Module
import me.youm.plumblossom.feature.module.ModuleCategory
import me.youm.plumblossom.feature.module.ModuleManager
import me.youm.plumblossom.feature.module.ModuleSign
import me.youm.plumblossom.feature.module.modules.movement.Sprint
import me.youm.plumblossom.utils.ColorUtil
import org.lwjgl.glfw.GLFW

/**
 * @author You_M
 */
@ModuleSign(
    name = "PlayerState",
    category = ModuleCategory.VISUAL,
    defaultState = true,
    keyBind = GLFW.GLFW_KEY_P,
    description = "the module can render player state text"
)
class PlayerState : Module() {
    val render = handler<RenderEvent2D> { event->
        val state = this.updatePlayerState()
        if(this.enabled && state != State.OTHER){
            this.mc.textRenderer.drawWithShadow(event.matrices,"[${state.message}]",5f,150f, ColorUtil.rainbow(15,0,1f,.3f,1f).rgb)
        }
    }
    private fun updatePlayerState() : State {
        val sprint = ModuleManager.getModuleByKClass(Sprint::class.java)
        val player = this.mc.player!!

        return if(sprint.enabled && sprint.toggled) State.SPRINT_TOGGLE
        else if(player.isSprinting && !sprint.toggled) State.SPRINT_VANILLA
        else if (player.abilities.flying) State.FLY
        else if(player.isFallFlying) State.FALL_FLY
        else if(player.isSneaking) State.SNEAK
        else State.OTHER
    }

    enum class State(var message:String){
        SPRINT_TOGGLE("Sprint Toggle"),SPRINT_VANILLA("Sprint Vanilla"),FLY("Fly"),FALL_FLY("Fall Fly"),
        SNEAK("Sneak"),OTHER("")
    }
}