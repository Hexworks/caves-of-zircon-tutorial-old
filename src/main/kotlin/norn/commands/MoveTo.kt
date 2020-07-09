package norn.commands

import org.hexworks.amethyst.api.entity.EntityType
import norn.extensions.GameCommand
import norn.extensions.GameEntity
import norn.world.GameContext
import org.hexworks.zircon.api.data.impl.Position3D

/**
 * A [GameCommand] representing moving [source] to [position].
 */
data class MoveTo(override val context: GameContext,
                  override val source: GameEntity<EntityType>,
                  val position: Position3D) : GameCommand<EntityType>
