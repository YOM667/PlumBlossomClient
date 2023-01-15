package event

/**
 * @author You_M
 */
open class Event {
    private var cancelled :Boolean = false
    private var type : Type? = null
}
enum class Type(val message: String){
    PRE("PRE"), POST("POST")
}