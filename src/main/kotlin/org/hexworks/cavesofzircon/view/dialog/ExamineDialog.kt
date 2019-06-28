package org.hexworks.cavesofzircon.view.dialog

import org.hexworks.cavesofzircon.attributes.types.iconTile
import org.hexworks.cavesofzircon.extensions.GameItem
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.GraphicalTilesetResources
import org.hexworks.zircon.api.graphics.BoxType
import org.hexworks.zircon.api.screen.Screen

class ExamineDialog(screen: Screen, item: GameItem) : Dialog(screen) {

    override val container = Components.panel()
            .withTitle("Examining ${item.name}")
            .withSize(25, 15)
            .withBoxType(BoxType.TOP_BOTTOM_DOUBLE)
            .wrapWithBox()
            .build().apply {
                addComponent(Components.textBox()
                        .withContentWidth(23)
                        .addHeader("Name", withNewLine = false)
                        .addInlineComponent(Components.icon()
                                .withIcon(item.iconTile)
                                .withTileset(GraphicalTilesetResources.nethack16x16())
                                .build())
                        .addInlineComponent(Components.label()
                                .withText(" ${item.name}")
                                .build())
                        .commitInlineElements()
                        .addNewLine()
                        .addHeader("Description", withNewLine = false)
                        .addParagraph(item.description, withNewLine = false))
            }
}
