package event

/**
 * @author You_M
 */
open class Event {
    /*
     * if the field was true , event poster will cancel
     */
    private var cancelled: Boolean = false
    /*
     * PRE and POST
     */
    private var type: Type? = null
}
enum class Type(val message: String) {
    PRE("PRE"), POST("POST")
}