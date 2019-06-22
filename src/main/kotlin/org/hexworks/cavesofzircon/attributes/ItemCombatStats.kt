package org.hexworks.cavesofzircon.attributes

import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.Component

data class ItemCombatStats(val attackValue: Int = 0,
                           val defenseValue: Int = 0,
                           val combatItemType: String) : DisplayableAttribute {

    override fun toComponent(width: Int): Component {
        return Components.textBox()
                .withContentWidth(width)
                .addParagraph("Type: $combatItemType", withNewLine = false)
                .addParagraph("Attack: $attackValue", withNewLine = false)
                .addParagraph("Defense: $defenseValue", withNewLine = false)
                .build()
    }

}
