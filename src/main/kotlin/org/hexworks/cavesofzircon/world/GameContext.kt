package org.hexworks.cavesofzircon.world

import org.hexworks.amethyst.api.Context
import org.hexworks.cavesofzircon.attributes.types.Player
import org.hexworks.cavesofzircon.extensions.GameEntity
import org.hexworks.zircon.api.screen.Screen
import org.hexworks.zircon.api.uievent.UIEvent

data class GameContext(val world: World,
                       val screen: Screen,
                       val uiEvent: UIEvent,
                       val player: GameEntity<Player>) : Context
