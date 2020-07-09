package norn.systems

import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Response
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType
import norn.attributes.CombatStats
import norn.attributes.types.ExperienceGainer
import norn.attributes.types.combatStats
import norn.attributes.types.experience
import norn.commands.EntityDestroyed
import norn.events.PlayerGainedLevel
import norn.extensions.GameCommand
import norn.extensions.attackValue
import norn.extensions.defenseValue
import norn.extensions.isPlayer
import norn.extensions.whenTypeIs
import norn.functions.logGameEvent
import norn.world.GameContext
import org.hexworks.cobalt.datatypes.extensions.map
import org.hexworks.zircon.internal.Zircon
import kotlin.math.min

object ExperienceAccumulator : BaseFacet<GameContext>() {

    override fun executeCommand(command: GameCommand<out EntityType>) = command.responseWhenCommandIs(EntityDestroyed::class) { (_, defender, attacker) ->
        attacker.whenTypeIs<ExperienceGainer> { experienceGainer ->
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
        Consumed
    }
}
