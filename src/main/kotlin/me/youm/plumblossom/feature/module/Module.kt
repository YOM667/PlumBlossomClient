package me.youm.plumblossom.feature.module

import event.EventBus
import event.Listenable

import net.minecraft.client.MinecraftClient


/**
 * @author You_M
 */
open class Module : Listenable {
    val mc = MinecraftClient.getInstance()!!
    val name: String
    val category: ModuleCategory
    val keyBind: Int
    private val moduleSign = javaClass.getAnnotation(ModuleSign::class.java)!!
    protected var toggled: Boolean = false
        set(value){
            if(field == value) return
            field = value
            if(value) {
                this.onEnable()
                EventBus.register(this)
            }else{
                this.onDisable()
                EventBus.unregister(this)
            }

        }
    val description: String

    init {
        this.name = this.moduleSign.name
        this.category = this.moduleSign.category
        this.keyBind = this.moduleSign.keyBind
        this.description = this.moduleSign.description
    }

    open fun onEnable(){}

    open fun onDisable(){}
    fun toggle() {
        this.toggled = !this.toggled
    }

    override var handleEvents: Boolean = toggled
}