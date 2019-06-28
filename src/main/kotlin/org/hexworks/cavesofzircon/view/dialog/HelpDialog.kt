package org.hexworks.cavesofzircon.view.dialog

import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.graphics.BoxType
import org.hexworks.zircon.api.screen.Screen

class HelpDialog(screen: Screen) : Dialog(screen) {

    override val container = Components.panel()
            .withTitle("Help")
            .withSize(44, 27)
            .withBoxType(BoxType.TOP_BOTTOM_DOUBLE)
            .wrapWithBox()
            .build().apply {
                addComponent(Components.textBox()
                        .withContentWidth(42)
                        .addNewLine()
                        .addHeader("Caves of Zircon")
                        .addParagraph("""
                            Descend to the Caves Of Zircon and collect as many Zircons as you can.
                             Find the exit (+) to win the game. Use what you find to avoid dying.""".trimIndent())
                        .addNewLine())

                addComponent(Components.textBox()
                        .withContentWidth(27)
                        .withPosition(0, 8)
                        .addHeader("Movement:")
                        .addListItem("wasd: Movement")
                        .addListItem("r: Move up")
                        .addListItem("f: Move down"))

                addComponent(Components.textBox()
                        .withContentWidth(40)
                        .withPosition(0, 16)
                        .addHeader("Navigation:")
                        .addListItem("[Tab]: Focus next")
                        .addListItem("[Shift] + [Tab]: Focus previous")
                        .addListItem("[Space]: Activate focused item"))

                addComponent(Components.textBox()
                        .withContentWidth(21)
                        .withPosition(28, 8)
                        .addHeader("Actions:")
                        .addListItem("(i)nventory")
                        .addListItem("(p)ick up")
                        .addListItem("(h)elp"))
            }
}
