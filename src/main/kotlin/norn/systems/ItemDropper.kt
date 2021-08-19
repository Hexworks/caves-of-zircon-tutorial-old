package norn.systems

import norn.attributes.types.removeItem
import norn.commands.DropItem
import norn.extensions.GameCommand
import norn.extensions.isPlayer
import norn.functions.logGameEvent
import norn.world.GameContext
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType

object ItemDropper : BaseFacet<GameContext>() {

    override fun executeCommand(command: GameCommand<out EntityType>) = command
        .responseWhenCommandIs(DropItem::class) { (context, itemHolder, item, position) ->
            if (itemHolder.removeItem(item)) {
                context.world.addEntity(item, position)
                val subject = if (itemHolder.isPlayer) "You" else "The $itemHolder"
                val verb = if (itemHolder.isPlayer) "drop" else "drops"
                logGameEvent("$subject $verb the $item.")
            }
            Consumed
        }
}
