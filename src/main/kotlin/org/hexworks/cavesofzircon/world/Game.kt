package org.hexworks.cavesofzircon.world

import org.hexworks.cavesofzircon.attributes.types.Player
import org.hexworks.cavesofzircon.extensions.GameEntity

class Game(val world: World,
           val player: GameEntity<Player>) {

    companion object {

        fun create(player: GameEntity<Player>,
                   world: World) = Game(
                world = world,
                player = player)
    }
}
