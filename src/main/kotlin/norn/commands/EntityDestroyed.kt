package norn.commands

import org.hexworks.amethyst.api.entity.EntityType
import norn.extensions.GameCommand
import norn.extensions.GameEntity
import norn.world.GameContext

data class EntityDestroyed(override val context: GameContext,
                           override val source: GameEntity<EntityType>,
                           val destroyer: GameEntity<EntityType>) : GameCommand<EntityType>
