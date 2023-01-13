package me.youm.plumblossom.feature.module

import net.minecraft.client.MinecraftClient


/**
 * @author You_M
 */
open class Module() {
    val mc = MinecraftClient.getInstance()!!
    val name: String
    val category : ModuleCategory
    val keyBind : Int
    private val moduleSign = javaClass.getAnnotation(ModuleSign::class.java)!!
    init {
        this.name = this.moduleSign.name
        this.category = this.moduleSign.category
        this.keyBind = this.moduleSign.keyBind
    }
    open fun onEnable(){

    }
    open fun onDisable(){

    }
}