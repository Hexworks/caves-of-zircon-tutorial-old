package org.hexworks.cavesofzircon.systems

import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Pass
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cavesofzircon.attributes.types.combatStats
import org.hexworks.cavesofzircon.commands.Attack
import org.hexworks.cavesofzircon.commands.Destroy
import org.hexworks.cavesofzircon.extensions.GameCommand
import org.hexworks.cavesofzircon.extensions.attackValue
import org.hexworks.cavesofzircon.extensions.defenseValue
import org.hexworks.cavesofzircon.extensions.isPlayer
import org.hexworks.cavesofzircon.extensions.whenHasNoHealthLeft
import org.hexworks.cavesofzircon.functions.logGameEvent
import org.hexworks.cavesofzircon.world.GameContext

object Attackable : BaseFacet<GameContext>() {

    override fun executeCommand(command: GameCommand<out EntityType>) = command.responseWhenCommandIs(Attack::class) {

        val (context, attacker, target) = it

        if (attacker.isPlayer || target.isPlayer) {

            val damage = Math.max(0, attacker.attackValue - target.defenseValue)
            val finalDamage = (Math.random() * damage).toInt() + 1
            target.combatStats.hp -= finalDamage

            logGameEvent("The $attacker hits the $target for $finalDamage!")

            target.whenHasNoHealthLeft {

                target.executeCommand(Destroy(
                        context = context,
                        source = attacker,
                        target = target,
                        cause = "after receiving a blow to the head"))
            }

            Consumed
        } else Pass
    }
}
