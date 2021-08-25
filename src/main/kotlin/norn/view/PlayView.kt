package norn.view

import norn.GameConfig
import norn.blocks.GameBlock
import norn.events.GameLogEvent
import norn.events.PlayerDied
import norn.events.PlayerGainedLevel
import norn.events.PlayerWonTheGame
import norn.functions.logDevGameEvent
import norn.view.dialog.LevelUpDialog
import norn.view.fragment.PlayerStatsFragment
import norn.world.Game
import norn.world.GameBuilder
import norn.world.GameState
import norn.world.MetaContext
import org.hexworks.cobalt.events.api.subscribe
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.GameComponents
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.extensions.onKeyboardEvent
import org.hexworks.zircon.api.extensions.onMouseEvent
import org.hexworks.zircon.api.game.ProjectionMode
import org.hexworks.zircon.api.mvc.base.BaseView
import org.hexworks.zircon.api.uievent.KeyboardEventType
import org.hexworks.zircon.api.uievent.MouseEventType
import org.hexworks.zircon.api.uievent.Processed
import org.hexworks.zircon.internal.Zircon

class PlayView(private val game: Game = GameBuilder.defaultGame()) : BaseView() {

    override val theme = GameConfig.THEME

    override fun onDock() {
        // I think this has to be right? Can we ever load this view when it isn't player turn?
        MetaContext.gameState = GameState.PLAYER_TURN

        screen.onKeyboardEvent(KeyboardEventType.KEY_PRESSED) { event, _ ->
            if (MetaContext.gameState == GameState.PLAYER_TURN) {
                game.world.update(screen, event, game)
            }
            Processed
        }

        screen.onMouseEvent(MouseEventType.MOUSE_CLICKED) { event, _ ->
            if (MetaContext.gameState == GameState.TARGETING) {
                game.world.update(screen, event, game)
            }
            MetaContext.gameState = GameState.PLAYER_TURN
            Processed
        }

        val sidebar = Components.panel()
            .withSize(GameConfig.SIDEBAR_WIDTH, GameConfig.WINDOW_HEIGHT)
            .wrapWithBox()
            .build()
        sidebar.addFragment(
            PlayerStatsFragment(
                width = sidebar.contentSize.width,
                player = game.player
            )
        )

        screen.addComponent(sidebar)

        val logArea = Components.logArea()
            .withTitle("Log")
            .wrapWithBox()
            .withSize(GameConfig.WINDOW_WIDTH - GameConfig.SIDEBAR_WIDTH, GameConfig.LOG_AREA_HEIGHT)
            .withAlignmentWithin(screen, ComponentAlignment.BOTTOM_RIGHT)
            .build()

        screen.addComponent(logArea)

        Zircon.eventBus.subscribe<GameLogEvent> { (text) ->
            logArea.addParagraph(
                paragraph = text,
                withNewLine = false,
                withTypingEffectSpeedInMs = 10
            )
        }

        Zircon.eventBus.subscribe<PlayerGainedLevel> {
            screen.openModal(LevelUpDialog(screen, game.player))
        }

        Zircon.eventBus.subscribe<PlayerWonTheGame> {
            replaceWith(WinView(it.zircons))
            close()
        }

        Zircon.eventBus.subscribe<PlayerDied> {
            replaceWith(LoseView(it.cause))
            close()
        }

        val gameComponent = GameComponents.newGameComponentBuilder<Tile, GameBlock>()
            .withGameArea(game.world)
            .withVisibleSize(game.world.visibleSize())
            .withProjectionMode(ProjectionMode.TOP_DOWN)
            .withAlignmentWithin(screen, ComponentAlignment.TOP_RIGHT)
            .build()

        screen.addComponent(gameComponent)
    }
}
