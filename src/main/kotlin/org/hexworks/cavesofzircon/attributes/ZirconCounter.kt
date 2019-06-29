package org.hexworks.cavesofzircon.attributes

import org.hexworks.cobalt.databinding.api.createPropertyFrom
import org.hexworks.cobalt.databinding.api.expression.concat
import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.Component

data class ZirconCounter(private val zirconCountProperty: Property<Int> = createPropertyFrom(0)) : DisplayableAttribute {

    var zirconCount: Int by zirconCountProperty.asDelegate()

    override fun toComponent(width: Int): Component {
        val zirconProp = createPropertyFrom("Zircons: ")
                .concat(zirconCountProperty) { it.value.toString() }
        return Components.header()
                .withText(zirconProp.value)
                .withSize(width, 1)
                .build().apply {
                    textProperty.updateFrom(zirconProp)
                }
    }
}
