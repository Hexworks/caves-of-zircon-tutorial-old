package norn.functions

import norn.GameConfig
import norn.RunMode
import norn.events.GameLogEvent
import org.hexworks.zircon.internal.Zircon

fun logGameEvent(text: String) {
    Zircon.eventBus.publish(GameLogEvent(text))
}


fun logDevGameEvent(text: String) {
    println(text)
}

fun logDebugGameEvent(text: String) {
    if (GameConfig.runMode == RunMode.DEBUG) {
        Zircon.eventBus.publish(GameLogEvent(text))
    }
}
