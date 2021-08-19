package norn.commands

import norn.extensions.GameCommand
import norn.extensions.GameEntity
import norn.world.GameContext
import org.hexworks.amethyst.api.entity.EntityType

data class Destroy(
    override val context: GameContext,
    override val source: GameEntity<EntityType>,
    val target: GameEntity<EntityType>,
    val cause: String = "natural causes."
) : GameCommand<EntityType>