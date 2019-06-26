package org.hexworks.cavesofzircon.attributes

import org.hexworks.cavesofzircon.extensions.toStringProperty
import org.hexworks.cobalt.databinding.api.createPropertyFrom
import org.hexworks.cobalt.databinding.api.expression.concat
import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.zircon.api.Components

data class CombatStats(val maxHpProperty: Property<Int>,
                       val hpProperty: Property<Int> = createPropertyFrom(maxHpProperty.value),
                       val attackValueProperty: Property<Int>,
                       val defenseValueProperty: Property<Int>) : DisplayableAttribute {
    val maxHp: Int by maxHpProperty.asDelegate()
    var hp: Int by hpProperty.asDelegate()
    val attackValue: Int by attackValueProperty.asDelegate()
    val defenseValue: Int by defenseValueProperty.asDelegate()

    override fun toComponent(width: Int) = Components.vbox()
            .withSize(width, 4)
            .build().apply {
                val hpLabel = Components.label()
                        .withSize(width, 1)
                        .build()
                val attackLabel = Components.label()
                        .withSize(width, 1)
                        .build()
                val defenseLabel = Components.label()
                        .withSize(width, 1)
                        .build()

                hpLabel.textProperty.updateFrom(createPropertyFrom("HP:  ")
                        .concat(hpProperty.toStringProperty())
                        .concat("/").concat(maxHpProperty))

                attackLabel.textProperty.updateFrom(createPropertyFrom("Att: ")
                        .concat(attackValueProperty.toStringProperty()))

                defenseLabel.textProperty.updateFrom(createPropertyFrom("Def: ")
                        .concat(defenseValueProperty.toStringProperty()))

                addComponent(Components.textBox()
                        .withContentWidth(width)
                        .addHeader("Combat Stats", false))
                addComponent(hpLabel)
                addComponent(attackLabel)
                addComponent(defenseLabel)

            }

    companion object {

        fun create(maxHp: Int, hp: Int = maxHp, attackValue: Int, defenseValue: Int) =
                CombatStats(
                        maxHpProperty = createPropertyFrom(maxHp),
                        hpProperty = createPropertyFrom(hp),
                        attackValueProperty = createPropertyFrom(attackValue),
                        defenseValueProperty = createPropertyFrom(defenseValue))
    }
}
