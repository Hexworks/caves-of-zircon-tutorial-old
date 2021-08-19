package norn.commands.spells

import norn.attributes.types.Combatant
import norn.attributes.types.EnergyUser
import norn.attributes.types.combatStats
import norn.extensions.GameEntity
import norn.world.GameContext

class Zap(
    override val context: GameContext,
    override val source: GameEntity<EnergyUser>,
    override val target: GameEntity<Combatant>
) : CombatantTargetedSpellAction<EnergyUser, Combatant>(context, source, target) {

    // TODO: destroy when dead, determine damage amount
    override fun visitCombatant(combatant: GameEntity<Combatant>) {
        combatant.combatStats.hp -= 10
    }
}