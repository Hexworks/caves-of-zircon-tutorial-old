package org.hexworks.cavesofzircon.events

import org.hexworks.cobalt.events.api.Event

data class PlayerDied(val cause: String) : Event
