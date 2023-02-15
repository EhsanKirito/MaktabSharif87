import android.util.Log

interface EventListener {
    fun update(int:Int)
}

class EventManager(vararg operations: String) {
    private var listeners = hashMapOf<String, ArrayList<EventListener>>()
    private var numbers = mutableListOf<Int>()

    init {
        for (operation in operations) {
            listeners[operation] = ArrayList<EventListener>()
        }
        for (i in 1..100){
            numbers.add(i)
        }
    }

    fun subscribe(eventType: String, listener: EventListener) {
        listeners[eventType]?.add(listener)
    }

    fun unSubscribe(eventType: String, listener: EventListener) {
        listeners[eventType]?.remove(listener)
    }

    fun notify(eventType: String) {
        for (i in 1..100) {
            val number = numbers[0]
            numbers.remove(number)
            listeners[eventType].let {
                if (it != null) {
                    for (listener in it) {
                        listener.update(number)
                    }
                    Thread.sleep(3000)
                }
            }
        }
    }

}

class Observers(private val id:String): EventListener {
    override fun update(int: Int) {
        Log.d("TAG", "$id Received: $int")
//        println("$id Received: $int")
    }
}

