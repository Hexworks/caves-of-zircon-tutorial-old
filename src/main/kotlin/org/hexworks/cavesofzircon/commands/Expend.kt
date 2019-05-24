package org.hexworks.cavesofzircon.commands

import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cavesofzircon.attributes.types.EnergyUser
import org.hexworks.cavesofzircon.extensions.GameCommand
import org.hexworks.cavesofzircon.extensions.GameEntity
import org.hexworks.cavesofzircon.world.GameContext

data class Expend(override val context: GameContext,
                  override val source: GameEntity<EnergyUser>,
                  val energy: Int) : GameCommand<EntityType>
