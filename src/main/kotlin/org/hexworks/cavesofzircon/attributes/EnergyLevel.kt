package org.hexworks.cavesofzircon.attributes

import org.hexworks.amethyst.api.Attribute
import org.hexworks.cobalt.databinding.api.createPropertyFrom

class EnergyLevel(initialEnergy: Int,
                  val maxEnergy: Int) : Attribute {

    var currentEnergy: Int
        get() = currentValueProperty.value
        set(value) {
            currentValueProperty.value = Math.min(value, maxEnergy)
        }

    private val currentValueProperty = createPropertyFrom(initialEnergy)

}
