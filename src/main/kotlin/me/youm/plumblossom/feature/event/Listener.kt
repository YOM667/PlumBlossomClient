package event

/**
 * @author You_M
 */

typealias Handler<T> = (T) -> Unit

interface Listenable

inline fun <reified T : Event> Listenable.handler(
    isListenable: Boolean = true,
    noinline handler: Handler<T>
){
    EventBus.addEventContainer<T>(
        type = T::class,
        container = EventContainer<T>(listenable = this, handler = handler, isListenable = isListenable)
    )
}
data class EventContainer<T>(
    val listenable: Listenable,
    val handler: Handler<T>,
    var isListenable: Boolean
)