package norn.commands

import org.hexworks.amethyst.api.entity.EntityType
import norn.extensions.GameCommand
import norn.extensions.GameEntity
import norn.world.GameContext

data class MoveUp(override val context: GameContext,
                  override val source: GameEntity<EntityType>) : GameCommand<EntityType>
