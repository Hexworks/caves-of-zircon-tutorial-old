package org.hexworks.cavesofzircon.view.fragment

import org.hexworks.cavesofzircon.attributes.types.Food
import org.hexworks.cavesofzircon.attributes.types.iconTile
import org.hexworks.cavesofzircon.extensions.GameItem
import org.hexworks.cavesofzircon.extensions.whenTypeIs
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.Fragment
import org.hexworks.zircon.api.graphics.Symbols

class InventoryRowFragment(width: Int, item: GameItem) : Fragment {

    val dropButton = Components.button()
            .wrapSides(false)
            .withText("Drop")
            .build()

    val eatButton = Components.button()
            .wrapSides(false)
            .withText("Eat")
            .build()

    override val root = Components.hbox()
            .withSpacing(1)
            .withSize(width, 1)
            .build().apply {
                addComponent(Components.icon()
                        .withIcon(item.iconTile))
                addComponent(Components.label()
                        .withSize(InventoryFragment.NAME_COLUMN_WIDTH, 1)
                        .withText(item.name))
                addComponent(dropButton)
                item.whenTypeIs<Food> {
                    addComponent(eatButton)
                }
            }

}
