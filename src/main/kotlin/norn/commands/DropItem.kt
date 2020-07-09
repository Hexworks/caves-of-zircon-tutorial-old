package norn.commands

import org.hexworks.amethyst.api.entity.EntityType
import norn.extensions.GameCommand
import norn.extensions.GameItem
import norn.extensions.GameItemHolder
import norn.world.GameContext
import org.hexworks.zircon.api.data.impl.Position3D

data class DropItem(override val context: GameContext,
                    override val source: GameItemHolder,
                    val item: GameItem,
                    val position: Position3D) : GameCommand<EntityType>
