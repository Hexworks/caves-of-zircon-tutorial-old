package norn.events

import org.hexworks.cobalt.events.api.Event

data class PlayerDied(val cause: String) : Event