package norn.view.fragment

import norn.attributes.types.CombatItem
import norn.attributes.types.Food
import norn.attributes.types.iconTile
import norn.extensions.GameItem
import norn.extensions.whenTypeIs
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.Fragment

class InventoryRowFragment(width: Int, item: GameItem) : Fragment {

    val dropButton = Components.button()
        .wrapSides(false)
        .withText("Drop")
        .build()

    val eatButton = Components.button()
        .wrapSides(false)
        .withText("Eat")
        .build()

    val equipButton = Components.button()
        .wrapSides(false)
        .withText("Equip")
        .build()

    val examineButton = Components.button()
        .wrapSides(false)
        .withText("Examine")
        .build()

    override val root = Components.hbox()
        .withSpacing(1)
        .withSize(width, 1)
        .build().apply {
            addComponent(
                Components.icon()
                    .withIcon(item.iconTile)
            )
            addComponent(
                Components.label()
                    .withSize(InventoryFragment.NAME_COLUMN_WIDTH, 1)
                    .withText(item.name)
            )
            addComponent(dropButton)
            addComponent(examineButton)
            item.whenTypeIs<Food> {
                addComponent(eatButton)
            }
            item.whenTypeIs<CombatItem> {
                addComponent(equipButton)
            }
        }
}
