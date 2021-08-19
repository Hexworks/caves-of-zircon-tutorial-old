package norn.systems

import norn.attributes.types.ItemHolder
import norn.attributes.types.inventory
import norn.commands.Destroy
import norn.commands.DropItem
import norn.extensions.GameCommand
import norn.extensions.position
import norn.extensions.whenTypeIs
import norn.world.GameContext
import org.hexworks.amethyst.api.Pass
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType

object LootDropper : BaseFacet<GameContext>() {

    override fun executeCommand(command: GameCommand<out EntityType>) = command
        .responseWhenCommandIs(Destroy::class) { (context, _, target) ->
            target.whenTypeIs<ItemHolder> { entity ->
                entity.inventory.items.forEach { item ->
                    entity.executeCommand(DropItem(context, entity, item, entity.position))
                }
            }
            // Pass means the other systems will receive the destroy command. Chain doesn't stop here
            Pass
        }
}
