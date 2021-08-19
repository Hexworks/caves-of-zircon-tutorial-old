package norn.commands

import norn.extensions.GameCommand
import norn.extensions.GameEntity
import norn.world.GameContext
import org.hexworks.amethyst.api.entity.EntityType

data class MoveUp(
    override val context: GameContext,
    override val source: GameEntity<EntityType>
) : GameCommand<EntityType>
