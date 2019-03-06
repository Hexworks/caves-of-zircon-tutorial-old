package org.hexworks.cavesofzircon.attributes

import org.hexworks.amethyst.api.Attribute
import org.hexworks.cobalt.databinding.api.createPropertyFrom
import org.hexworks.zircon.api.data.impl.Position3D

class EntityPosition(initialPosition: Position3D = Position3D.unknown()) : Attribute { // 1
    private val positionProperty = createPropertyFrom(initialPosition) // 2

    var position: Position3D by positionProperty.asDelegate() // 3
}
