package norn.systems

import norn.commands.Interact
import norn.extensions.GameCommand
import norn.functions.logGameEvent
import norn.world.GameContext
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType

object Interactable : BaseFacet<GameContext>() {

    override fun executeCommand(command: GameCommand<out EntityType>) =
        command.responseWhenCommandIs(Interact::class) { (context, source, target) ->
            logGameEvent(target.description)
            Consumed
        }
}
