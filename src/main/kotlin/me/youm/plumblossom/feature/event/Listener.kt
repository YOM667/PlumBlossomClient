package event

/**
 * @author You_M
 */
typealias Handler<T> = (T) -> Unit

/**
 * want to be listening need to implement the interface
 */
interface Listenable{
    var handleEvents : Boolean
}

/**
 * in implement listenable class, you can invoke the function to register your callback function
 */
inline fun <reified T : Event> Listenable.handler(
    noinline handler: Handler<T>
){
    EventBus.addEventContainer(
        type = T::class,
        container = EventContainer(listenable = this, handler = handler)
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
)