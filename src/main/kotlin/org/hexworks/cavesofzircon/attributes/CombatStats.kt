package org.hexworks.cavesofzircon.attributes

import org.hexworks.amethyst.api.Attribute
import org.hexworks.cobalt.databinding.api.createPropertyFrom
import org.hexworks.cobalt.databinding.api.property.Property

data class CombatStats(val maxHpProperty: Property<Int>,
                       val hpProperty: Property<Int> = createPropertyFrom(maxHpProperty.value),
                       val attackValueProperty: Property<Int>,
                       val defenseValueProperty: Property<Int>) : Attribute {
    val maxHp: Int by maxHpProperty.asDelegate()
    var hp: Int by hpProperty.asDelegate()
    val attackValue: Int by attackValueProperty.asDelegate()
    val defenseValue: Int by defenseValueProperty.asDelegate()

    companion object {

        fun create(maxHp: Int, hp: Int = maxHp, attackValue: Int, defenseValue: Int) =
                CombatStats(
                        maxHpProperty = createPropertyFrom(maxHp),
                        hpProperty = createPropertyFrom(hp),
                        attackValueProperty = createPropertyFrom(attackValue),
                        defenseValueProperty = createPropertyFrom(defenseValue))
    }
}
