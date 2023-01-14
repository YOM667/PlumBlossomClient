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
        val handlers = storageMap.computeIfAbsent(eventClass) { ArrayList() }
        val hook = handle as EventStorage<in Event>
        if (!handlers.contains(hook)) {
            handlers.add(hook)
        }
        storageMap.forEach{logger.info("value: ${it.value} key ${it.key} ")}
    }
    fun unregisterListener(listenable: Listenable) {
        for ((key, handlerList) in storageMap) {
            handlerList.removeIf { it.handlerClass == listenable }
            storageMap[key] = handlerList
        }
    }
    fun <T : Event> callEvent(event: T): T {
        val target = storageMap[event::class] ?: return event
        for (eventStorage in target) {
            if (!eventStorage.handlerClass.handleEvents()) {
                continue
            }
            runCatching {
                println("曹尼玛")
                eventStorage.handler(event)
            }.onFailure {
                logger.error("Exception while executing handler.", it)
            }
        }
        return event
    }
}