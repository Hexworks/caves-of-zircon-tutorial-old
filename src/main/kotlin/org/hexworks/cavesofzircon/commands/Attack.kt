package org.hexworks.cavesofzircon.commands

import org.hexworks.cavesofzircon.attributes.types.Combatant
import org.hexworks.cavesofzircon.extensions.GameEntity
import org.hexworks.cavesofzircon.world.GameContext

data class Attack(override val context: GameContext,
                  override val source: GameEntity<Combatant>,
                  override val target: GameEntity<Combatant>) : EntityAction<Combatant, Combatant>
