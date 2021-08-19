package norn.commands.spells

import norn.attributes.types.Combatant
import norn.attributes.types.EnergyUser
import norn.attributes.types.Player
import norn.commands.EntityAction
import norn.extensions.GameEntity
import norn.world.GameContext
import org.hexworks.amethyst.api.entity.Entity

// TODO: these need energy costs
// TODO: mana system in general Q.Q
abstract class CombatantTargetedSpellAction<S : EnergyUser, T : Combatant>(
    context: GameContext,
    override val source: GameEntity<S>,
    override var target: GameEntity<T>
) : EntityAction<S, T> {

    open fun visitCombatant(combatant: GameEntity<Combatant>) {}
}