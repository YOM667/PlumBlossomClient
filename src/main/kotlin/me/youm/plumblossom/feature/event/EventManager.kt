package me.youm.plumblossom.feature.event

import me.youm.plumblossom.PlumBlossom.logger
import kotlin.reflect.KClass
/**
 * @author You_M
 */
object EventManager {
    private val storageMap = mutableMapOf<KClass<out Event>,ArrayList<EventStorage<in Event>>>()

    fun <T : Event> unregisterStorage(eventClass: KClass<out Event>, handle: EventStorage<T>) {
        storageMap[eventClass]?.remove(handle as EventStorage<in Event>)
    }
    fun <T : Event> registerStorage(eventClass: KClass<out Event>,handle : EventStorage<T>){
        val list = storageMap.computeIfAbsent(eventClass) { ArrayList() }.toMutableList()
        val hook = handle as EventStorage<in Event>
        if (!list.contains(hook)) {
            list += hook
        }
    }
    fun <T : Event> callEvent(event: T): T {
        val target = storageMap[event::class] ?: return event
        for (eventStorage in target) {
            if (!eventStorage.handlerClass.handleEvents()) {
                continue
            }
            runCatching {
                eventStorage.handler(event)
            }.onFailure {
                logger.error("Exception while executing handler.", it)
            }
        }
        return event
    }
}