package norn.systems

import org.hexworks.amethyst.api.CommandResponse
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Pass
import org.hexworks.amethyst.api.Response
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType
import norn.attributes.types.Player
import norn.commands.MoveCamera
import norn.commands.MoveTo
import norn.extensions.GameCommand
import norn.extensions.position
import norn.world.GameContext
import norn.extensions.tryActionsOn
import org.hexworks.cobalt.datatypes.extensions.map

object Movable : BaseFacet<GameContext>() {

    override fun executeCommand(command: GameCommand<out EntityType>) = command.responseWhenCommandIs(MoveTo::class) { (context, entity, position) ->
        val world = context.world
        val previousPosition = entity.position
        var result: Response = Pass
        world.fetchBlockAt(position).map { block ->
            if (block.isOccupied) {
                result = entity.tryActionsOn(context, block.occupier.get())
            } else {
                if (world.moveEntity(entity, position)) {
                    result = Consumed
                    if (entity.type == Player) {
                        result = CommandResponse(MoveCamera(
                                context = context,
                                source = entity,
                                previousPosition = previousPosition))
                    }
                }
            }
        }
        result
    }
}
