package me.youm.plumblossom.feature.module

import me.youm.plumblossom.feature.event.Listenable

import net.minecraft.client.MinecraftClient


/**
 * @author You_M
 */
open class Module : Listenable {
    protected val mc = MinecraftClient.getInstance()!!
    private val moduleSign = javaClass.getAnnotation(ModuleSign::class.java)!!
    val name: String = this.moduleSign.name
    val category: ModuleCategory = this.moduleSign.category
    val keyBind: Int = this.moduleSign.keyBind
    val description: String = this.moduleSign.description
    private var enabled: Boolean = this.moduleSign.defaultState
        set(value){
            if(field == value) return
            field = value
            if(value) {
                this.onEnable()
            }else{
                this.onDisable()
            }
        }

    open fun onEnable(){}

    open fun onDisable(){}
    fun toggle() {
        this.enabled = !this.enabled
    }

    override var handleEvents: Boolean = enabled
}