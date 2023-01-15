package event

import kotlin.reflect.KClass

/**
 * @author You_M
 */
object EventBus {

    private val eventContainer = mutableMapOf<KClass<out Event>,ArrayList<EventContainer<in Event>>>()

    fun <T : Event> addEventContainer(type:KClass<out Event>, container: EventContainer<T>){
        val newContainer = eventContainer.computeIfAbsent(type) { ArrayList() }
        val eventContainer = container as EventContainer<in Event>
        if (!newContainer.contains(eventContainer)) {
            newContainer.add(eventContainer)
        }
    }

    fun register(listenable: Listenable){
        search(listenable,true)
    }
    fun unregister(listenable: Listenable){
        search(listenable,false)
    }
    private fun search(listenable: Listenable, flag : Boolean){
        eventContainer.values.forEach { list->
            list.forEach { container ->
                if (container.listenable === listenable) {
                    container.isListenable = flag
                }
            }
        }
    }
    fun <T : Event> post(event: T) : T{
        val target = eventContainer[event::class] ?: return event
        for (container in target) {
            if (!container.isListenable) {
                continue
            }
            runCatching {
                container.handler(event)
            }.onFailure {
                println("Exception while executing event.handler. $it")
            }
        }
        return event
    }
}