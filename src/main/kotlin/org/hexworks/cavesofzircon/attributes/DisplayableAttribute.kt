package org.hexworks.cavesofzircon.attributes

import org.hexworks.amethyst.api.Attribute
import org.hexworks.zircon.api.component.Component

interface DisplayableAttribute : Attribute {

    fun toComponent(width: Int): Component

}
