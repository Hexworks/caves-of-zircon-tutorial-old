package org.hexworks.cavesofzircon.attributes

import org.hexworks.cavesofzircon.attributes.types.Armor
import org.hexworks.cavesofzircon.attributes.types.Weapon
import org.hexworks.cavesofzircon.attributes.types.attackValue
import org.hexworks.cavesofzircon.attributes.types.defenseValue
import org.hexworks.cavesofzircon.attributes.types.iconTile
import org.hexworks.cavesofzircon.extensions.GameCombatItem
import org.hexworks.cavesofzircon.extensions.GameEntity
import org.hexworks.cavesofzircon.extensions.whenTypeIs
import org.hexworks.cobalt.databinding.api.createPropertyFrom
import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.Component

class Equipment(initialWeapon: GameEntity<Weapon>,
                initialArmor: GameEntity<Armor>) : DisplayableAttribute {

    private val weaponProperty: Property<GameEntity<Weapon>> = createPropertyFrom(initialWeapon)
    private val armorProperty: Property<GameEntity<Armor>> = createPropertyFrom(initialArmor)

    val attackValue: Int
        get() = weaponProperty.value.attackValue + armorProperty.value.attackValue

    val defenseValue: Int
        get() = weaponProperty.value.defenseValue + armorProperty.value.defenseValue

    val armorName: String
        get() = armorProperty.value.name

    val weaponName: String
        get() = weaponProperty.value.name

    val weapon: GameEntity<Weapon> by weaponProperty.asDelegate()
    val armor: GameEntity<Armor> by armorProperty.asDelegate()

    private val weaponStats: String
        get() = " A: ${weapon.attackValue} D: ${weapon.defenseValue}"

    private val armorStats: String
        get() = " A: ${armor.attackValue} D: ${armor.defenseValue}"

    fun equip(inventory: Inventory, combatItem: GameCombatItem): GameCombatItem {
        combatItem.whenTypeIs<Weapon> {
            return equipWeapon(inventory, it)
        }
        combatItem.whenTypeIs<Armor> {
            return equipArmor(inventory, it)
        }
        throw IllegalStateException("Combat item is not Weapon or Armor.")
    }

    private fun equipWeapon(inventory: Inventory, newWeapon: GameEntity<Weapon>): GameCombatItem {
        val oldWeapon = weapon
        inventory.removeItem(newWeapon)
        inventory.addItem(oldWeapon)
        weaponProperty.value = newWeapon
        return oldWeapon
    }

    private fun equipArmor(inventory: Inventory, newArmor: GameEntity<Armor>): GameCombatItem {
        val oldArmor = armor
        inventory.removeItem(newArmor)
        inventory.addItem(oldArmor)
        armorProperty.value = newArmor
        return oldArmor
    }

    override fun toComponent(width: Int): Component {
        val weaponIcon = Components.icon().withIcon(weaponProperty.value.iconTile).build()
        val weaponNameLabel = Components.label()
                .withText(weaponName)
                .withSize(width - 2, 1)
                .build()
        val weaponStatsLabel = Components.label()
                .withText(weaponStats)
                .withSize(width - 1, 1)
                .build()

        val armorIcon = Components.icon().withIcon(armorProperty.value.iconTile).build()
        val armorNameLabel = Components.label()
                .withText(armorName)
                .withSize(width - 2, 1)
                .build()
        val armorStatsLabel = Components.label()
                .withText(armorStats)
                .withSize(width - 1, 1)
                .build()

        weaponProperty.onChange {
            weaponIcon.iconProperty.value = weapon.iconTile
            weaponNameLabel.textProperty.value = weapon.name
            weaponStatsLabel.textProperty.value = weaponStats
        }

        armorProperty.onChange {
            armorIcon.iconProperty.value = armor.iconTile
            armorNameLabel.textProperty.value = armor.name
            armorStatsLabel.textProperty.value = armorStats
        }

        return Components.textBox()
                .withContentWidth(width)
                .addHeader("Weapon", withNewLine = false)
                .addInlineComponent(weaponIcon)
                .addInlineComponent(weaponNameLabel)
                .commitInlineElements()
                .addInlineComponent(weaponStatsLabel)
                .commitInlineElements()
                .addNewLine()
                .addHeader("Armor", withNewLine = false)
                .addInlineComponent(armorIcon)
                .addInlineComponent(armorNameLabel)
                .commitInlineElements()
                .addInlineComponent(armorStatsLabel)
                .commitInlineElements()
                .build()
    }

}
