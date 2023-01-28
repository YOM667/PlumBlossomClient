package me.youm.plumblossom

import me.youm.plumblossom.feature.event.Listenable
import me.youm.plumblossom.feature.event.StartGameEvent
import me.youm.plumblossom.feature.event.StopGameEvent
import me.youm.plumblossom.feature.event.handler
import me.youm.plumblossom.feature.module.ModuleManager
import net.fabricmc.api.ModInitializer
import net.minecraft.client.MinecraftClient
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object PlumBlossom : ModInitializer, Listenable{

    const val NAME = "PlumBlossom"
    const val VERSION = "0.0.1-alpha"
    val AUTHORS = listOf("YouM")
    val logger :Logger = LogManager.getLogger()

    val startGame = handler<StartGameEvent> {
        runCatching {
            logger.info("Mod name: $NAME Mod Authors ${AUTHORS.joinToString()}")
            logger.info(MinecraftClient.getInstance().resourceManager)
        }
    }

    val stopGame = handler<StopGameEvent> {
        logger.info("PlumBlossom shutdown")
    }

    override var handleEvents: Boolean = true

    /**
     * Runs the mod initializer.
     */
    override fun onInitialize() {
        ModuleManager.loadModules()
    }
}