package norn.commands

import norn.attributes.types.ItemHolder
import norn.extensions.GameCommand
import norn.extensions.GameItemHolder
import norn.world.GameContext
import org.hexworks.zircon.api.data.impl.Position3D

data class PickItemUp(
    override val context: GameContext,
    override val source: GameItemHolder,
    val position: Position3D
) : GameCommand<ItemHolder>
