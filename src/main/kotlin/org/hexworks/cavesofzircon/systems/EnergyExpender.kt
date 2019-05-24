package org.hexworks.cavesofzircon.systems

import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Response
import org.hexworks.amethyst.api.base.BaseActor
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cavesofzircon.attributes.EnergyLevel
import org.hexworks.cavesofzircon.attributes.types.EnergyUser
import org.hexworks.cavesofzircon.attributes.types.energyLevel
import org.hexworks.cavesofzircon.commands.Destroy
import org.hexworks.cavesofzircon.commands.Expend
import org.hexworks.cavesofzircon.extensions.GameCommand
import org.hexworks.cavesofzircon.extensions.GameEntity
import org.hexworks.cavesofzircon.extensions.whenTypeIs
import org.hexworks.cavesofzircon.world.GameContext

object EnergyExpender : BaseActor<GameContext>(EnergyLevel::class) {

    override fun executeCommand(command: GameCommand<out EntityType>): Response {
        return command.responseWhenCommandIs(Expend::class) { (context, entity, energy) ->
            entity.energyLevel.currentEnergy -= energy
            checkStarvation(context, entity, entity.energyLevel)
            Consumed
        }
    }

    override fun update(entity: GameEntity<EntityType>, context: GameContext): Boolean {
        entity.whenTypeIs<EnergyUser> {
            entity.executeCommand(Expend(
                    context = context,
                    source = it,
                    energy = 2))
        }
        return true
    }

    private fun checkStarvation(context: GameContext,
                                entity: GameEntity<EntityType>,
                                energyLevel: EnergyLevel) {
        if (energyLevel.currentEnergy <= 0) {
            entity.executeCommand(Destroy(
                    context = context,
                    source = entity,
                    target = entity,
                    cause = "because of starvation"))
        }
    }
}
