package org.hexworks.cavesofzircon.attributes

import org.hexworks.cavesofzircon.extensions.toStringProperty
import org.hexworks.cobalt.databinding.api.createPropertyFrom
import org.hexworks.cobalt.databinding.api.expression.concat
import org.hexworks.zircon.api.Components

class EnergyLevel(initialEnergy: Int,
                  val maxEnergy: Int) : DisplayableAttribute {

    var currentEnergy: Int
        get() = currentValueProperty.value
        set(value) {
            currentValueProperty.value = Math.min(value, maxEnergy)
        }

    private val currentValueProperty = createPropertyFrom(initialEnergy)

    override fun toComponent(width: Int) = Components.vbox()
            .withSize(width, 2)
            .build().apply {
                val hungerLabel = Components.label()
                        .withSize(width, 1)
                        .build()

                hungerLabel.textProperty.updateFrom(currentValueProperty.toStringProperty()
                        .concat("/").concat(maxEnergy))

                addComponent(Components.textBox()
                        .withContentWidth(width)
                        .addHeader("Hunger", false))
                addComponent(hungerLabel)

            }
}
