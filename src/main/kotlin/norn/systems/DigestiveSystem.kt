package  norn.systems

import norn.attributes.EnergyLevel
import norn.attributes.types.energy
import norn.attributes.types.energyLevel
import norn.commands.Eat
import norn.extensions.GameCommand
import norn.extensions.isPlayer
import norn.functions.logGameEvent
import norn.world.GameContext
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType

object DigestiveSystem : BaseFacet<GameContext>(EnergyLevel::class) {

    override fun executeCommand(command: GameCommand<out EntityType>) =
        command.responseWhenCommandIs(Eat::class) { (_, entity, food) ->
            entity.energyLevel.currentEnergy += food.energy
            val verb = if (entity.isPlayer) {
                "You eat"
            } else "The $entity eats"
            logGameEvent("$verb the $food.")
            Consumed
        }
}
