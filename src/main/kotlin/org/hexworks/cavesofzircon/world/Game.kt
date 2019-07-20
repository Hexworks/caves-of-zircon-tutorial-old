package org.hexworks.cavesofzircon.world

import org.hexworks.cavesofzircon.attributes.types.Player
import org.hexworks.cavesofzircon.extensions.GameEntity

class Game(
    val world: World,
    val player: GameEntity<Player>? = null
) {

    companion object {
        fun create(
            world: World
        ) = Game(
            world = world
        )

        fun create(
            player: GameEntity<Player>,
            world: World
        ) = Game(
            world = world,
            player = player
        )
    }
}
