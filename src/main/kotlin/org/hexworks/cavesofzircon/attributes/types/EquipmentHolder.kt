package org.hexworks.cavesofzircon.attributes.types

import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cavesofzircon.attributes.Equipment
import org.hexworks.cavesofzircon.attributes.Inventory
import org.hexworks.cavesofzircon.extensions.GameCombatItem
import org.hexworks.cavesofzircon.extensions.GameEquipmentHolder

interface EquipmentHolder : EntityType

fun GameEquipmentHolder.equip(inventory: Inventory, item: GameCombatItem): GameCombatItem {
    return equipment.equip(inventory, item)
}

val GameEquipmentHolder.equipment: Equipment
    get() = findAttribute(Equipment::class).get()
