package norn.extensions

import org.hexworks.cobalt.databinding.api.createPropertyFrom
import org.hexworks.cobalt.databinding.api.property.Property

fun Property<Int>.toStringProperty(): Property<String> {
    val intProp = this
    return createPropertyFrom("").apply {
        this.updateFrom(intProp) {
            it.toString()
        }
    }
}