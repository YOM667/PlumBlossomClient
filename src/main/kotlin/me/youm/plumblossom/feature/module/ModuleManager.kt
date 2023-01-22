package me.youm.plumblossom.feature.module

import event.Listenable
import event.handler
import me.youm.plumblossom.PlumBlossom.logger
import me.youm.plumblossom.feature.event.KeyEvent
import me.youm.plumblossom.utils.ClassUtil
import net.minecraft.client.MinecraftClient
import org.lwjgl.glfw.GLFW


/**
 * @author You_M
 */

object ModuleManager : Listenable {
    val mc = MinecraftClient.getInstance()
    private val modules = mutableListOf<Module>()
    private val moduleClassMap = hashMapOf<Class<*>, Module>()
    fun loadModules(){
        ClassUtil.packageScanner("${this.javaClass.packageName}.modules",Module::class.java)
            .forEach(this::registerModule)
    }

    val keyEvent = handler<KeyEvent> {event->
        if (event.action == GLFW.GLFW_PRESS) {
            moduleClassMap.filter { it.value.keyBind == event.key.code }.forEach{
                it.value.toggle()
            }
        }
    }

    private fun registerModule(moduleClass: Class<out Module>) {
        runCatching {
            moduleClass.newInstance()
        }.onSuccess {module->
            modules += module
            moduleClassMap[moduleClass] = module
        }.onFailure (logger::error)

    }
    fun getModuleByCategory(category: ModuleCategory) = modules.filter { it.category == category }

    fun getModuleByName(name: String) = modules.find { it.name == name }

    fun <T : Module> getModuleByKClass(kClass : Class<T>) : T = moduleClassMap[kClass] as T


    override var handleEvents: Boolean = true

}