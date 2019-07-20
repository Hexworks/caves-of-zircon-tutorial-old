package org.hexworks.cavesofzircon.view

import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.cavesofzircon.GameConfig
import org.hexworks.cavesofzircon.attributes.types.Player
import org.hexworks.cavesofzircon.world.GameBuilder
import org.hexworks.cavesofzircon.world.GameContext
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.extensions.onComponentEvent
import org.hexworks.zircon.api.graphics.BoxType
import org.hexworks.zircon.api.mvc.base.BaseView
import org.hexworks.zircon.api.uievent.ComponentEventType
import org.hexworks.zircon.api.uievent.Processed

class WinView(
    private val player: Entity<Player, GameContext>,
    private val zircons: Int
) : BaseView() {

    override val theme = GameConfig.THEME

    override fun onDock() {
        val msg = "You won!"
        val header = Components.textBox()
                .withContentWidth(GameConfig.WINDOW_WIDTH / 2)
                .addHeader(msg)
                .addNewLine()
                .addParagraph("Congratulations! You have escaped from Caves of Zircon!", withNewLine = false)
                .addParagraph("You've managed to find $zircons Zircons.")
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

        restartButton.onComponentEvent(ComponentEventType.ACTIVATED) {
            replaceWith(
                PlayView(
                    player,
                    GameBuilder(
                        worldSize = GameConfig.WORLD_SIZE
                    ).buildGame()
                )
            )
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
