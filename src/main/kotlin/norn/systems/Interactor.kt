package norn.systems

import norn.commands.Interact
import norn.extensions.GameCommand
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

            return Consumed
        }

        return Pass
    }
}