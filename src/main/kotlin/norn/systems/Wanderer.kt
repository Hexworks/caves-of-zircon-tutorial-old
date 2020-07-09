package norn.systems

import org.hexworks.amethyst.api.base.BaseBehavior
import org.hexworks.amethyst.api.entity.EntityType
import norn.commands.MoveTo
import norn.extensions.GameEntity
import norn.extensions.position
import norn.extensions.sameLevelNeighborsShuffled
import norn.world.GameContext

object Wanderer : BaseBehavior<GameContext>() {

    override fun update(entity: GameEntity<EntityType>, context: GameContext): Boolean {
        val pos = entity.position
        if (pos.isUnknown().not()) {
            entity.executeCommand(MoveTo(
                    context = context,
                    source = entity,
                    position = pos.sameLevelNeighborsShuffled().first()))
            return true
        }
        return false
    }
}
