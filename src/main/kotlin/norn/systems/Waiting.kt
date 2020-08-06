package norn.systems

import norn.commands.Wait
import norn.extensions.GameCommand
import norn.world.GameContext
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType

// Take a turn off
object Waiting : BaseFacet<GameContext>() {
    override fun executeCommand(command: GameCommand<out EntityType>) = command.responseWhenCommandIs(Wait::class) { (context, _, target) ->
        Consumed
    }
}