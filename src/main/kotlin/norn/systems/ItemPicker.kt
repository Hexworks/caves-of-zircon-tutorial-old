package norn.systems

import norn.attributes.types.addItem
import norn.commands.PickItemUp
import norn.extensions.GameCommand
import norn.extensions.isPlayer
import norn.functions.logDevGameEvent
import norn.functions.logGameEvent
import norn.world.GameContext
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cobalt.datatypes.extensions.map

object ItemPicker : BaseFacet<GameContext>() {

    override fun executeCommand(command: GameCommand<out EntityType>) =
        command.responseWhenCommandIs(PickItemUp::class) { (context, itemHolder, position) ->
            val world = context.world
            world.findTopItem(position).map { item ->
                logDevGameEvent("looking for item at $position")
                if (itemHolder.addItem(item)) {
                    world.removeEntity(item)
                    val subject = if (itemHolder.isPlayer) "You" else "The $itemHolder"
                    val verb = if (itemHolder.isPlayer) "pick up" else "picks up"
                    logGameEvent("$subject $verb the $item.")
                }
            }
            Consumed
        }


}
