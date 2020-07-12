package norn.systems

import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType
import norn.commands.Dig
import norn.extensions.GameCommand
import norn.world.GameContext
import org.hexworks.amethyst.api.entity.Entity

object Diggable : BaseFacet<GameContext>() {

    override fun executeCommand(command: GameCommand<out EntityType>) = command.responseWhenCommandIs(Dig::class) { (context, _, target) ->
        context.world.removeEntity(target)
        Consumed
    }
}
