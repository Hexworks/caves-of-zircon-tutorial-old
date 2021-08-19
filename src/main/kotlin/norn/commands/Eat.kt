package norn.commands

import norn.attributes.types.EnergyUser
import norn.attributes.types.Food
import norn.extensions.GameEntity
import norn.world.GameContext

data class Eat(
    override val context: GameContext,
    override val source: GameEntity<EnergyUser>,
    override var target: GameEntity<Food>
) : EntityAction<EnergyUser, Food>
