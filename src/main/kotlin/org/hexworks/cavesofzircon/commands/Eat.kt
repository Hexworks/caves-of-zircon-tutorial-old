package org.hexworks.cavesofzircon.commands

import org.hexworks.cavesofzircon.attributes.types.Food
import org.hexworks.cavesofzircon.attributes.types.EnergyUser
import org.hexworks.cavesofzircon.extensions.GameEntity
import org.hexworks.cavesofzircon.world.GameContext

data class Eat(override val context: GameContext,
               override val source: GameEntity<EnergyUser>,
               override val target: GameEntity<Food>) : EntityAction<EnergyUser, Food>
