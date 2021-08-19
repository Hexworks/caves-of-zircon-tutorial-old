package norn.commands.spells

import norn.attributes.types.Combatant
import norn.attributes.types.EnergyUser
import norn.attributes.types.combatStats
import norn.commands.Destroy
import norn.extensions.GameEntity
import norn.extensions.whenHasNoHealthLeft
import norn.functions.logGameEvent
import norn.world.GameContext

class Zap(
    override val context: GameContext,
    override val source: GameEntity<EnergyUser>,
    override var target: GameEntity<Combatant>
) : CombatantTargetedSpellAction<EnergyUser, Combatant>(context, source, target) {

    // TODO: destroy when dead, determine damage amount
    override fun visitCombatant(combatant: GameEntity<Combatant>) {
        combatant.combatStats.hp -= 10
        logGameEvent("$combatant takes 10 Zap damage!")
        combatant.whenHasNoHealthLeft {

            target.executeCommand(
                Destroy(
                    context = context,
                    source = source,
                    target = target,
                    cause = "after being zapped with lightning"
                )
            )
        }
    }
}