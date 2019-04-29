package org.hexworks.cavesofzircon.entities

import org.hexworks.amethyst.api.base.BaseEntity
import org.hexworks.cavesofzircon.attributes.types.FogOfWarType
import org.hexworks.cavesofzircon.builders.GameTileRepository
import org.hexworks.cavesofzircon.extensions.position
import org.hexworks.cavesofzircon.world.Game
import org.hexworks.cavesofzircon.world.GameContext
import org.hexworks.zircon.api.Layers
import org.hexworks.zircon.api.graphics.Layer
import java.util.concurrent.ConcurrentHashMap

class FogOfWar(game: Game) : BaseEntity<FogOfWarType, GameContext>(FogOfWarType) {

    private val world = game.world
    private val player = game.player
    private val size = game.world.actualSize()

    private val fowPerLevel = ConcurrentHashMap<Int, Layer>().also { fows ->
        repeat(size.zLength) { level ->
            val fow = Layers.newBuilder()
                    .withSize(size.to2DSize())
                    .build()
                    .fill(GameTileRepository.UNREVEALED)
            fows[level] = fow
            world.pushOverlayAt(fow, level)
        }
    }

    init {
        updateFOW()
    }

    private fun updateFOW() {
        world.findVisiblePositionsFor(player).forEach {
            fowPerLevel[player.position.z]?.setTileAt(it, GameTileRepository.EMPTY)
        }
    }

    override fun update(context: GameContext): Boolean {
        updateFOW()
        return true
    }
}
