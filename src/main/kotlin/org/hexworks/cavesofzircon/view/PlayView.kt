package org.hexworks.cavesofzircon.view

import org.hexworks.cavesofzircon.GameConfig
import org.hexworks.cavesofzircon.blocks.GameBlock
import org.hexworks.cavesofzircon.world.Game
import org.hexworks.zircon.api.ColorThemes
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.GameComponents
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.game.ProjectionMode
import org.hexworks.zircon.api.mvc.base.BaseView

// TODO
class PlayView(private val game: Game) : BaseView() {

    override val theme = ColorThemes.arc()

    override fun onDock() {

        val sidebar = Components.panel()
                .withSize(GameConfig.SIDEBAR_WIDTH, GameConfig.WINDOW_HEIGHT)
                .wrapWithBox()
                .build()

        screen.addComponent(sidebar)

        val logArea = Components.logArea()
                .withTitle("Log")
                .wrapWithBox()
                .withSize(GameConfig.WINDOW_WIDTH - GameConfig.SIDEBAR_WIDTH, GameConfig.LOG_AREA_HEIGHT)
                .withAlignmentWithin(screen, ComponentAlignment.BOTTOM_RIGHT)
                .build()

        screen.addComponent(logArea)

        // TODO
        val gameComponent = GameComponents.newGameComponentBuilder<Tile, GameBlock>()
                .withGameArea(game.world)
                .withVisibleSize(game.visibleSize)
                .withProjectionMode(ProjectionMode.TOP_DOWN)
                .withAlignmentWithin(screen, ComponentAlignment.TOP_RIGHT)
                .build()
    }
}
