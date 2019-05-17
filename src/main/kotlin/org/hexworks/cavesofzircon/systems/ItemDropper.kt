package org.hexworks.cavesofzircon.systems

import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cavesofzircon.attributes.types.inventory
import org.hexworks.cavesofzircon.attributes.types.removeItem
import org.hexworks.cavesofzircon.commands.DropItem
import org.hexworks.cavesofzircon.extensions.GameCommand
import org.hexworks.cavesofzircon.extensions.isPlayer
import org.hexworks.cavesofzircon.functions.logGameEvent
import org.hexworks.cavesofzircon.world.GameContext

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
