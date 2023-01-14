package me.youm.plumblossom.feature.module

import me.youm.plumblossom.PlumBlossom
import me.youm.plumblossom.utils.ClassUtil
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

/**
 * @author You_M
 */
object ModuleManager {
    val modules = mutableListOf<Module>()

    private val moduleClassMap = hashMapOf<KClass<*>, Module>()

    fun loadModules(){
        ClassUtil.packageScanner("${this.javaClass.packageName}.modules",Module::class)
            .forEach(this::registerModule)

        PlumBlossom.logger.info(modules)
    }

    private fun registerModule(moduleClass: KClass<out Module>) {
        val module = moduleClass.createInstance()
        modules += module
        moduleClassMap[moduleClass] = module
    }
    fun getModuleByCategory(category: ModuleCategory) = modules.filter { it.category == category }

    fun getModuleByName(name: String) = modules.find { it.name == name }

    fun getModuleByKClass(kClass : KClass<out Module>) = moduleClassMap[kClass]

}