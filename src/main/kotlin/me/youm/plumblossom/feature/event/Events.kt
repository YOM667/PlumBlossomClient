package me.youm.plumblossom.feature.event

import net.minecraft.client.util.InputUtil
import net.minecraft.client.util.math.MatrixStack

/**
 * @author You_M
 */
class GameTickUpdateEvent : Event()
data class KeyEvent(val key: InputUtil.Key, val action: Int, val mods: Int) : Event()

data class RenderEvent2D(var matrices : MatrixStack, var partialTicks : Float) : Event()
class StartGameEvent : Event()

class StopGameEvent : Event()