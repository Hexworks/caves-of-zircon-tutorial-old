package norn.functions

import norn.events.GameLogEvent
import org.hexworks.zircon.internal.Zircon

fun logGameEvent(text: String) {
    Zircon.eventBus.publish(GameLogEvent(text))
}

// TODO: update this with a debug log event and add listener
fun debugGameEvent(text: String) {
    Zircon.eventBus.publish(GameLogEvent(text))
}
