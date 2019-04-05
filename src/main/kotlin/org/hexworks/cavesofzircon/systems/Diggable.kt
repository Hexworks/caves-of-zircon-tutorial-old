package org.hexworks.cavesofzircon.systems

import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cavesofzircon.commands.Dig
import org.hexworks.cavesofzircon.extensions.GameCommand
import org.hexworks.cavesofzircon.world.GameContext

object Diggable : BaseFacet<GameContext>() {

    override fun executeCommand(command: GameCommand<out EntityType>) = command.responseWhenCommandIs(Dig::class) { (context, _, target) ->
        context.world.removeEntity(target)
        Consumed
    }
}
