package norn.commands

import org.hexworks.amethyst.api.entity.EntityType
import norn.extensions.GameCommand
import norn.extensions.GameEntity
import norn.world.GameContext
import org.hexworks.zircon.api.data.impl.Position3D

data class MoveCamera(override val context: GameContext,
                      override val source: GameEntity<EntityType>,
                      val previousPosition: Position3D) : GameCommand<EntityType>
