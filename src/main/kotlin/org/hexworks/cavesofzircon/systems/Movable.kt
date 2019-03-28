package org.hexworks.cavesofzircon.systems

import org.hexworks.amethyst.api.CommandResponse
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Pass
import org.hexworks.amethyst.api.Response
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cavesofzircon.attributes.types.Player
import org.hexworks.cavesofzircon.commands.MoveCamera
import org.hexworks.cavesofzircon.commands.MoveTo
import org.hexworks.cavesofzircon.extensions.GameCommand
import org.hexworks.cavesofzircon.extensions.position
import org.hexworks.cavesofzircon.world.GameContext

object Movable : BaseFacet<GameContext>() {

    override fun executeCommand(command: GameCommand<out EntityType>) = command.responseWhenCommandIs(MoveTo::class) { (context, entity, position) ->
        val world = context.world
        val previousPosition = entity.position      // 1
        var result: Response = Pass
        if (world.moveEntity(entity, position)) {
            result = if (entity.type == Player) {   // 2
                CommandResponse(MoveCamera(         // 3
                        context = context,
                        source = entity,
                        previousPosition = previousPosition))
            } else Consumed
        }
        result
    }
}
