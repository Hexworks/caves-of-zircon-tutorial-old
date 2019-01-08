package org.hexworks.cavesofzircon.world

import org.hexworks.cavesofzircon.GameConfig
import org.hexworks.zircon.api.data.impl.Size3D

class Game(val world: World) {

    companion object {

        fun create(worldSize: Size3D = GameConfig.WORLD_SIZE,
                   visibleSize: Size3D = GameConfig.WORLD_SIZE) = Game(WorldBuilder(worldSize)
                .makeCaves()
                .build(visibleSize))
    }
}
