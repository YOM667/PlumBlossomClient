package me.youm.plumblossom.utils

import me.youm.plumblossom.PlumBlossom
import net.minecraft.client.MinecraftClient
import net.minecraft.text.Text

object ChatUtil {
    private val mc = MinecraftClient.getInstance()!!
    private const val prefix = "[${PlumBlossom.NAME}]"
    fun sendMessage(message : Any){
        mc.player?.sendMessage(Text.translatable("$prefix: $message"))
    }
    fun sendMessage(vararg message: Any){
        message.forEach(ChatUtil::sendMessage)
    }

    fun sendChatMessage(message : Any){
        mc.player?.networkHandler?.sendChatMessage(message.toString())
    }
    fun sendChatMessage(vararg message : Any){
        message.forEach(ChatUtil::sendChatMessage)
    }
    fun sendCommandMessage(message: Any){
        mc.player?.networkHandler?.sendCommand(message.toString())
    }
    fun sendCommandMessage(vararg message: Any){
        message.forEach(ChatUtil::sendCommandMessage)
    }
}