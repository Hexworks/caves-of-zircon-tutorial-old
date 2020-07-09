package norn.systems

import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Response
import org.hexworks.amethyst.api.base.BaseActor
import org.hexworks.amethyst.api.entity.EntityType
import norn.attributes.EnergyLevel
import norn.attributes.types.EnergyUser
import norn.attributes.types.energyLevel
import norn.commands.Destroy
import norn.commands.Expend
import norn.extensions.GameCommand
import norn.extensions.GameEntity
import norn.extensions.whenTypeIs
import norn.world.GameContext

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
