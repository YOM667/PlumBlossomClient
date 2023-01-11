package me.youm.plumblossom

import net.fabricmc.api.ModInitializer
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class PlumBlossom : ModInitializer {
    companion object{
        const val NAME = "PlumBlossom"
        val AUTHORS = arrayOf("YouM");
        val log :Logger = LogManager.getLogger()
    }
    override fun onInitialize() {
        log.info("Hello Kotlin Fabric MOD")
    }
}