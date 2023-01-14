package me.youm.plumblossom.feature.event

import net.minecraft.client.util.InputUtil

/**
 * @author You_M
 */
class GameTickUpdateEvent : Event()
class KeyEvent(val key: InputUtil.Key, val action: Int, val mods: Int) : Event()