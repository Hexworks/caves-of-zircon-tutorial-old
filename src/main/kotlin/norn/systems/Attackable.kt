package norn.systems

import norn.attributes.types.combatStats
import norn.commands.Attack
import norn.commands.Destroy
import norn.extensions.*
import norn.functions.logGameEvent
import norn.world.GameContext
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Pass
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType

object Attackable : BaseFacet<GameContext>() {

    override fun executeCommand(command: GameCommand<out EntityType>) = command.responseWhenCommandIs(Attack::class) {

        val (context, attacker, target) = it

        if (attacker.isPlayer || target.isPlayer) {

            val damage = Math.max(0, attacker.attackValue - target.defenseValue)
            val finalDamage = (Math.random() * damage).toInt() + 1
            target.combatStats.hp -= finalDamage
            logGameEvent("The $attacker hits the $target for $finalDamage!")

            target.whenHasNoHealthLeft {

                target.executeCommand(
                    Destroy(
                        context = context,
                        source = attacker,
                        target = target,
                        cause = "after receiving a blow to the head"
                    )
                )
            }

            Consumed
        } else Pass
    }
}
