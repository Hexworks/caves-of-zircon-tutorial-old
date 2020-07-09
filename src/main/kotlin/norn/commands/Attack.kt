package norn.commands

import org.hexworks.amethyst.api.entity.EntityType
import norn.attributes.types.Combatant
import norn.extensions.GameEntity
import norn.world.GameContext

data class Attack(override val context: GameContext,
                  override val source: GameEntity<Combatant>,
                  override val target: GameEntity<Combatant>) : EntityAction<Combatant, Combatant> {
}