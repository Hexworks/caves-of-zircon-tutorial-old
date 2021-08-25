package norn.systems

import norn.attributes.types.*
import norn.commands.Interact
import norn.extensions.GameCommand
import norn.functions.asInteractableEntity
import norn.world.GameContext
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Pass
import org.hexworks.amethyst.api.Response
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType

object Interactor : BaseFacet<GameContext>() {

    override fun executeCommand(command: GameCommand<out EntityType>): Response {
        if (command is Interact) {
            val (context, source, target) = command
            (target.type as ContainerEntity<Interactable>).contained.interact(context, source)
            return Consumed
        }

        return Pass
    }
}