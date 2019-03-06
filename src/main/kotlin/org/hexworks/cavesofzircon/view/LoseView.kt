package org.hexworks.cavesofzircon.view

import org.hexworks.zircon.api.ColorThemes
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.extensions.onComponentEvent
import org.hexworks.zircon.api.graphics.BoxType
import org.hexworks.zircon.api.mvc.base.BaseView
import org.hexworks.zircon.api.uievent.ComponentEventType
import org.hexworks.zircon.api.uievent.Processed

class LoseView : BaseView() {

    override val theme = ColorThemes.arc()

    override fun onDock() {
        val msg = "Game Over"
        val header = Components.textBox()
                .withContentWidth(30)
                .addHeader(msg)
                .withAlignmentWithin(screen, ComponentAlignment.CENTER)
                .build()
        val restartButton = Components.button()
                .withAlignmentAround(header, ComponentAlignment.BOTTOM_LEFT)
                .withText("Restart")
                .wrapSides(false)
                .wrapWithBox()
                .withBoxType(BoxType.SINGLE)
                .build()
        val exitButton = Components.button()
                .withAlignmentAround(header, ComponentAlignment.BOTTOM_RIGHT)
                .withText("Quit")
                .wrapSides(false)
                .wrapWithBox()
                .withBoxType(BoxType.SINGLE)
                .build()

        // TODO: tutorial
        restartButton.onComponentEvent(ComponentEventType.ACTIVATED) {
            replaceWith(PlayView())
            close()
            Processed
        }

        exitButton.onComponentEvent(ComponentEventType.ACTIVATED) {
            System.exit(0)
            Processed
        }

        screen.addComponent(header)
        screen.addComponent(restartButton)
        screen.addComponent(exitButton)
    }
}
