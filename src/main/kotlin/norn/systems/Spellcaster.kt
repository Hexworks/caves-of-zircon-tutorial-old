package norn.systems

import norn.attributes.types.Combatant
import norn.attributes.types.EnergyUser
import norn.attributes.types.acceptVisitor
import norn.commands.spells.CombatantTargetedSpellAction
import norn.extensions.GameCommand
import norn.world.GameContext
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Pass
import org.hexworks.amethyst.api.Response
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType

object Spellcaster : BaseFacet<GameContext>() {

    override fun executeCommand(command: GameCommand<out EntityType>): Response {
        if (command is CombatantTargetedSpellAction<*, *>) {
            val (_, source, target) = command
            target
            target.acceptVisitor(command as CombatantTargetedSpellAction<EnergyUser, Combatant>)
            return Consumed
        }

        return Pass
    }
}