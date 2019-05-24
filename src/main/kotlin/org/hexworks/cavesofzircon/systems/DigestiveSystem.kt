package  org.hexworks.cavesofzircon.systems

import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cavesofzircon.attributes.EnergyLevel
import org.hexworks.cavesofzircon.attributes.types.energy
import org.hexworks.cavesofzircon.attributes.types.energyLevel
import org.hexworks.cavesofzircon.commands.Eat
import org.hexworks.cavesofzircon.extensions.GameCommand
import org.hexworks.cavesofzircon.extensions.isPlayer
import org.hexworks.cavesofzircon.functions.logGameEvent
import org.hexworks.cavesofzircon.world.GameContext

object DigestiveSystem : BaseFacet<GameContext>(EnergyLevel::class) {

    override fun executeCommand(command: GameCommand<out EntityType>) = command.responseWhenCommandIs(Eat::class) { (_, entity, food) ->
        entity.energyLevel.currentEnergy += food.energy
        val verb = if (entity.isPlayer) {
            "You eat"
        } else "The $entity eats"
        logGameEvent("$verb the $food.")
        Consumed
    }
}
