package norn.attributes.types

import org.hexworks.amethyst.api.entity.EntityType
import norn.attributes.Inventory
import norn.extensions.GameItem
import norn.extensions.GameItemHolder

interface ItemHolder : EntityType

fun GameItemHolder.addItem(item: GameItem) = inventory.addItem(item)

fun GameItemHolder.removeItem(item: GameItem) = inventory.removeItem(item)

val GameItemHolder.inventory: Inventory
    get() = findAttribute(Inventory::class).get()
