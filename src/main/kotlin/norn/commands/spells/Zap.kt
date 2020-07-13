package norn.commands.spells

import norn.attributes.types.Combatant
import norn.attributes.types.EnergyUser
import norn.attributes.types.combatStats
import norn.attributes.types.heal
import norn.extensions.GameEntity
import norn.world.GameContext

class Zap(override val context: GameContext,
           override val source: GameEntity<EnergyUser>,
           override val target: GameEntity<Combatant>) : CombatantTargetedSpellAction<EnergyUser, Combatant>(context, source, target) {

    override fun visitCombatant(combatant: GameEntity<Combatant>) {
        combatant.combatStats.hp -= 10
    }
}