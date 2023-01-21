package me.youm.plumblossom

import me.youm.plumblossom.feature.module.ModuleManager
import net.fabricmc.api.ModInitializer
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object PlumBlossom : ModInitializer {

    const val NAME = "PlumBlossom"
    val AUTHORS = listOf("YouM")
    val logger :Logger = LogManager.getLogger()
    override fun onInitialize() {
        ModuleManager.loadModules()
        logger.info("Hello Kotlin Fabric MOD")
        logger.info("Mod name: $NAME Mod Authors ${AUTHORS.joinToString()}")
    }
}