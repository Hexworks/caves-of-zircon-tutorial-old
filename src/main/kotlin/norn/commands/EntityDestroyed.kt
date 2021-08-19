package norn.commands

import norn.extensions.GameCommand
import norn.extensions.GameEntity
import norn.world.GameContext
import org.hexworks.amethyst.api.entity.EntityType

data class EntityDestroyed(
    override val context: GameContext,
    override val source: GameEntity<EntityType>,
    val destroyer: GameEntity<EntityType>
) : GameCommand<EntityType>
