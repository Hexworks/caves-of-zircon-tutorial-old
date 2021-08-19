package norn.commands

import norn.extensions.GameEntity
import norn.world.GameContext
import org.hexworks.amethyst.api.entity.EntityType

data class Wait(
    override val context: GameContext,
    override val source: GameEntity<EntityType>,
    override var target: GameEntity<EntityType>
) : EntityAction<EntityType, EntityType>
