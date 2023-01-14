package me.youm.plumblossom.feature.module

import me.youm.plumblossom.feature.event.Listenable
import net.minecraft.client.MinecraftClient


/**
 * @author You_M
 */
open class Module : Listenable{
    val mc = MinecraftClient.getInstance()!!
    val name: String
    val category: ModuleCategory
    val keyBind: Int
    private val moduleSign = javaClass.getAnnotation(ModuleSign::class.java)!!
    protected var toggled: Boolean = false

    init {
        this.name = this.moduleSign.name
        this.category = this.moduleSign.category
        this.keyBind = this.moduleSign.keyBind
    }

    open fun onEnable() {

    }

    open fun onDisable() {

    }

    fun toggle() {
        this.toggled = !this.toggled
        if(toggled){
            this.onDisable()
        } else{
            this.onDisable()
        }
    }
}