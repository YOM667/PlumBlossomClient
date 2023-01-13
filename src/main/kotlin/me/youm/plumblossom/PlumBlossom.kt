package me.youm.plumblossom

import me.youm.plumblossom.feature.module.ModuleManager
import net.fabricmc.api.ModInitializer
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class PlumBlossom : ModInitializer {

    companion object{
        const val NAME = "PlumBlossom"
        val AUTHORS = "YouM"
        val logger :Logger = LogManager.getLogger()
        val moduleManager : ModuleManager = ModuleManager()
    }
    override fun onInitialize() {
        moduleManager.loadModules()
        logger.info("Hello Kotlin Fabric MOD")
    }
}