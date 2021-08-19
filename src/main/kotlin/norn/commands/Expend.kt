package norn.commands

import norn.attributes.types.EnergyUser
import norn.extensions.GameCommand
import norn.extensions.GameEntity
import norn.world.GameContext
import org.hexworks.amethyst.api.entity.EntityType

data class Expend(
    override val context: GameContext,
    override val source: GameEntity<EnergyUser>,
    val energy: Int
) : GameCommand<EntityType>
