package norn.commands

import org.hexworks.amethyst.api.entity.EntityType
import norn.extensions.GameCommand
import norn.extensions.GameEntity
import norn.world.GameContext

data class Destroy(override val context: GameContext,
                   override val source: GameEntity<EntityType>,
                   val target: GameEntity<EntityType>,
                   val cause: String = "natural causes.") : GameCommand<EntityType> {
}