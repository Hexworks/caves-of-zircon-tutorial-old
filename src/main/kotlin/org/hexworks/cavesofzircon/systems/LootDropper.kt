package org.hexworks.cavesofzircon.systems

import org.hexworks.amethyst.api.Pass
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cavesofzircon.attributes.types.ItemHolder
import org.hexworks.cavesofzircon.attributes.types.inventory
import org.hexworks.cavesofzircon.commands.Destroy
import org.hexworks.cavesofzircon.commands.DropItem
import org.hexworks.cavesofzircon.extensions.GameCommand
import org.hexworks.cavesofzircon.extensions.position
import org.hexworks.cavesofzircon.extensions.whenTypeIs
import org.hexworks.cavesofzircon.world.GameContext

object LootDropper : BaseFacet<GameContext>() {

    override fun executeCommand(command: GameCommand<out EntityType>) = command
            .responseWhenCommandIs(Destroy::class) { (context, _, target) ->
                target.whenTypeIs<ItemHolder> { entity ->
                    entity.inventory.items.forEach { item ->
                        entity.executeCommand(DropItem(context, entity, item, entity.position))
                    }
                }
                Pass
            }
}
