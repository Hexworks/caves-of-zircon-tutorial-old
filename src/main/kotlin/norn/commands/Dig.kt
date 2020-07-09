package norn.commands

import org.hexworks.amethyst.api.entity.EntityType
import norn.extensions.GameEntity
import norn.world.GameContext

data class Dig(override val context: GameContext,
               override val source: GameEntity<EntityType>,
               override val target: GameEntity<EntityType>) : EntityAction<EntityType, EntityType>
