package org.hexworks.cavesofzircon.functions

import org.hexworks.cavesofzircon.events.GameLogEvent
import org.hexworks.zircon.internal.Zircon

fun logGameEvent(text: String) {
    Zircon.eventBus.publish(GameLogEvent(text))
}
