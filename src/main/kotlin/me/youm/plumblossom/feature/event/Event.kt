package me.youm.plumblossom.feature.event

/**
 * @author You_M
 * 事件类
 */
typealias Handler<T> = (T) -> Unit

open class Event {
    private var cancelled :Boolean = false
    private var type : Type? = null
}

enum class Type(val message: String){
    PRE("PRE"), POST("POST")
}
interface Listenable {
    fun handleEvents(): Boolean = parent()?.handleEvents() ?: true
    fun parent(): Listenable? = null
}
data class EventStorage<T : Event>(
    val handlerClass: Listenable,
    val handler : Handler<T>
)

inline fun <reified  T : Event> Listenable.handler(
    noinline call : Handler<T>
){
    EventManager.registerStorage(T::class,EventStorage(this,call))
}