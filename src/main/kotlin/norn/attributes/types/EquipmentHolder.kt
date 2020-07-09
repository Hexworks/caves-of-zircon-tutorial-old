package norn.attributes.types

import org.hexworks.amethyst.api.entity.EntityType
import norn.attributes.Equipment
import norn.attributes.Inventory
import norn.extensions.GameCombatItem
import norn.extensions.GameEquipmentHolder

interface EquipmentHolder : EntityType

fun GameEquipmentHolder.equip(inventory: Inventory, item: GameCombatItem): GameCombatItem {
    return equipment.equip(inventory, item)
}

val GameEquipmentHolder.equipment: Equipment
    get() = findAttribute(Equipment::class).get()
