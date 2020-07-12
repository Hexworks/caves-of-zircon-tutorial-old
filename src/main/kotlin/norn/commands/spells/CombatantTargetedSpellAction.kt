package norn.commands.spells

import norn.attributes.types.Combatant
import norn.attributes.types.EnergyUser
import norn.commands.EntityAction
import norn.extensions.GameEntity
import norn.world.GameContext
import org.hexworks.amethyst.api.entity.EntityType

abstract class CombatantTargetedSpellAction<S: EntityType, T: EntityType>(context: GameContext,
                source: GameEntity<EnergyUser>, target: GameEntity<Combatant>) : EntityAction<S, T> {

    open fun visitCombatant(combatant: GameEntity<Combatant>) {}
}