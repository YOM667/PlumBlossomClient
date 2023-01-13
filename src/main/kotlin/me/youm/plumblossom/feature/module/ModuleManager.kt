package me.youm.plumblossom.feature.module

import me.youm.plumblossom.PlumBlossom
import me.youm.plumblossom.utils.ClassUtil

/**
 * @author You_M
 */
class ModuleManager {
    val modules = mutableListOf<Module>()

    private val moduleClassMap = hashMapOf<Class<*>, Module>()

    fun loadModules(){
        ClassUtil.packageScanner("${this.javaClass.packageName}.modules",Module::class.java)
            .forEach(this::registerModule)

        PlumBlossom.logger.info(modules)
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