package norn.commands

import org.hexworks.amethyst.api.entity.EntityType
import norn.extensions.GameCommand
import norn.extensions.GameItemHolder
import norn.world.GameContext
import org.hexworks.zircon.api.data.impl.Position3D

data class InspectInventory(override val context: GameContext,
                            override val source: GameItemHolder,
                            val position: Position3D) : GameCommand<EntityType>
