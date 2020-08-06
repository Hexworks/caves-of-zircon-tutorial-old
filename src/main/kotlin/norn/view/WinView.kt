package norn.view

import norn.GameConfig
import norn.world.GameBuilder
import norn.world.GameState
import norn.world.MetaContext
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.extensions.onComponentEvent
import org.hexworks.zircon.api.graphics.BoxType
import org.hexworks.zircon.api.mvc.base.BaseView
import org.hexworks.zircon.api.uievent.ComponentEventType
import org.hexworks.zircon.api.uievent.Processed
import kotlin.system.exitProcess

class WinView(private val zircons: Int) : BaseView() {

    override val theme =  GameConfig.THEME

    override fun onDock() {
        MetaContext.gameState = GameState.LOSE
        val msg = "You won!"
        val header = Components.textBox()
                .withContentWidth( GameConfig.WINDOW_WIDTH / 2)
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
            replaceWith(PlayView(GameBuilder(
                    worldSize =  GameConfig.WORLD_SIZE).buildGame()))
            close()
            Processed
        }

        exitButton.onComponentEvent(ComponentEventType.ACTIVATED) {
            exitProcess(0)
        }

        screen.addComponent(header)
        screen.addComponent(restartButton)
        screen.addComponent(exitButton)
    }
}
