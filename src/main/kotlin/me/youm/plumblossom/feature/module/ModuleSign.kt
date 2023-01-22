package me.youm.plumblossom.feature.module

import org.lwjgl.glfw.GLFW

@Retention
annotation class ModuleSign(
    val name: String,
    val category: ModuleCategory,
    val keyBind: Int = GLFW.GLFW_KEY_UNKNOWN,
    val description: String,
    val defaultState : Boolean
)
