package event

/**
 * @author You_M
 */
typealias Handler<T> = (T) -> Unit

/**
 * want to be listening need to implement the interface
 */
interface Listenable

/**
 * in implement listenable class, you can invoke the function to register your callback function
 */
inline fun <reified T : Event> Listenable.handler(
    isListenable: Boolean = true,
    noinline handler: Handler<T>
){
    EventBus.addEventContainer<T>(
        type = T::class,
        container = EventContainer<T>(listenable = this, handler = handler, isListenable = isListenable)
    )
}

/**
 * the class storage Event Information
 * @see Listenable
 * @see Handler
 */
data class EventContainer<T>(
    val listenable: Listenable,
    val handler: Handler<T>,
    var isListenable: Boolean
)