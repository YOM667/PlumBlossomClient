package me.youm.plumblossom.feature.event

import me.youm.plumblossom.PlumBlossom.logger
import kotlin.reflect.KClass

/**
 * The object is used for [register] and [unregister] listener or [post] Event
 *
 * TIP:The object was learned from EventBus, LiquidBounce and some other's code
 * @author You_M
 */
object EventBus {
    /**
     * eventContainer to storage "implement [Event]'s kotlin class"  and a ArrayList to storage "[EventContainer] classes"
     */
    private val eventContainers = mutableMapOf<KClass<out Event>,ArrayList<EventContainer<in Event>>>()

    /**
     *
     * @param type
     * @param container
     */
    fun <T : Event> addEventContainer(type:KClass<out Event>, container: EventContainer<T>){
        val newContainer = eventContainers.computeIfAbsent(type) { ArrayList() }
        val eventContainer = container as EventContainer<in Event>
        if (!newContainer.contains(eventContainer)) {
            newContainer.add(eventContainer)
        }
    }

    /**
     * by the method make EventBus to listen [listenable]
     * @param listenable the listen class you want
     */
    fun register(listenable: Listenable){
        search(listenable,true)
    }
    /**
     * by the method make EventBus to cancel listen [listenable]
     * @param listenable the cancel listen class you want
     */
    fun unregister(listenable: Listenable){
        search(listenable,false)
    }
    private fun search(listenable: Listenable, flag : Boolean){
        eventContainers.values.forEach { list->
            list.forEach { container ->
                if (container.listenable === listenable) {
                    container.listenable.handleEvents = flag
                }
            }
        }
    }

    /**
     * The method makes publisher publish [Event] to EventBus
     * @param event event type to publish
     * @return an event class
     */
    fun <T : Event> post(event: T) : T{
        val target = eventContainers[event::class] ?: return event
        for (container in target) {
            if (!container.listenable.handleEvents) {
                continue
            }
            runCatching {
                container.handler(event)
            }.onFailure {
                logger.error("Exception while executing me.youm.plumblossom.feature.event.handler. $it")
            }
        }
        return event
    }
}