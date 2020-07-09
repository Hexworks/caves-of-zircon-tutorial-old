package norn.events

import org.hexworks.cobalt.events.api.Event

data class PlayerWonTheGame(val zircons: Int) : Event