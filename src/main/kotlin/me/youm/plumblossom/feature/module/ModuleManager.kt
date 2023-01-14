package me.youm.plumblossom.feature.module

import me.youm.plumblossom.PlumBlossom
import me.youm.plumblossom.feature.event.KeyEvent
import me.youm.plumblossom.feature.event.Listenable
import me.youm.plumblossom.feature.event.handler
import me.youm.plumblossom.utils.ClassUtil
import org.lwjgl.glfw.GLFW


/**
 * @author You_M
 */

object ModuleManager : Listenable{
    val modules = mutableListOf<Module>()
    private val moduleClassMap = hashMapOf<Class<*>, Module>()
    fun loadModules(){
        ClassUtil.packageScanner("${this.javaClass.packageName}.modules",Module::class.java)
            .forEach(this::registerModule)

        PlumBlossom.logger.info(modules)
    }

    val keyEvent = handler<KeyEvent> {event->
        if (event.action == GLFW.GLFW_PRESS) {
            moduleClassMap.filter { it.value.keyBind == event.key.code }.forEach{
                it.value.toggle()
            }
        }
    }

    private fun registerModule(moduleClass: Class<out Module>) {
        val module = moduleClass.newInstance()
        modules += module
        moduleClassMap[moduleClass] = module
    }
    fun getModuleByCategory(category: ModuleCategory) = modules.filter { it.category == category }

    fun getModuleByName(name: String) = modules.find { it.name == name }

    fun getModuleByKClass(kClass : Class<out Module>) = moduleClassMap[kClass]

}