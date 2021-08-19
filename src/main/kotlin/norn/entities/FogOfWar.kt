package norn.entities

import norn.attributes.types.FogOfWarType
import norn.builders.GameTileRepository
import norn.extensions.position
import norn.world.Game
import norn.world.GameContext
import org.hexworks.amethyst.api.base.BaseEntity
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

    override fun update(context: GameContext): Boolean {
        updateFOW()
        return true
    }

    private fun updateFOW() {
        world.findVisiblePositionsFor(player).forEach {
            fowPerLevel[player.position.z]?.setTileAt(it, GameTileRepository.EMPTY)
        }
    }
}
