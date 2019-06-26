package org.hexworks.cavesofzircon.systems

import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Pass
import org.hexworks.amethyst.api.Response
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cavesofzircon.attributes.CombatStats
import org.hexworks.cavesofzircon.attributes.types.ExperienceGainer
import org.hexworks.cavesofzircon.attributes.types.combatStats
import org.hexworks.cavesofzircon.attributes.types.experience
import org.hexworks.cavesofzircon.commands.EntityDestroyed
import org.hexworks.cavesofzircon.events.PlayerGainedLevel
import org.hexworks.cavesofzircon.extensions.GameCommand
import org.hexworks.cavesofzircon.extensions.attackValue
import org.hexworks.cavesofzircon.extensions.defenseValue
import org.hexworks.cavesofzircon.extensions.isPlayer
import org.hexworks.cavesofzircon.extensions.whenTypeIs
import org.hexworks.cavesofzircon.functions.logGameEvent
import org.hexworks.cavesofzircon.world.GameContext
import org.hexworks.cobalt.datatypes.extensions.map
import org.hexworks.zircon.internal.Zircon
import kotlin.math.min

object ExperienceAccumulator : BaseFacet<GameContext>() {

    override fun executeCommand(command: GameCommand<out EntityType>) = command.responseWhenCommandIs(EntityDestroyed::class) { (_, defender, attacker) ->
        var response: Response = Pass
        attacker.whenTypeIs<ExperienceGainer> { experienceGainer ->
            response = Consumed
            val xp = experienceGainer.experience
            val stats = experienceGainer.combatStats
            val defenderHp = defender.findAttribute(CombatStats::class).map { it.maxHp }.orElse(0)
            val amount = (defenderHp + defender.attackValue + defender.defenseValue) - xp.currentLevel * 2
            if (amount > 0) {
                xp.currentXP += amount
                while (xp.currentXP > Math.pow(xp.currentLevel.toDouble(), 1.5) * 20) {
                    xp.currentLevel++
                    logGameEvent("$attacker advanced to level ${xp.currentLevel}.")
                    stats.hpProperty.value = min(stats.hp + xp.currentLevel * 2, stats.maxHp)
                    if (attacker.isPlayer) {
                        Zircon.eventBus.publish(PlayerGainedLevel)
                    }
                }
            }

        }
        response
    }
}
