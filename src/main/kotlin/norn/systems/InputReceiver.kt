package norn.systems

import norn.extensions.*
import norn.functions.logDevGameEvent
import org.hexworks.amethyst.api.base.BaseBehavior
import org.hexworks.amethyst.api.entity.EntityType
import norn.world.GameContext
import org.hexworks.zircon.api.uievent.KeyCode
import org.hexworks.zircon.api.uievent.KeyboardEvent
import norn.view.dialog.HelpDialog
import norn.world.Game
import norn.world.GameState
import norn.world.MetaContext
import org.hexworks.cobalt.logging.api.LoggerFactory
import org.hexworks.zircon.api.screen.Screen
import org.hexworks.zircon.api.uievent.MouseEvent

object InputReceiver : BaseBehavior<GameContext>() {

    private val logger = LoggerFactory.getLogger(this::class)

    override fun update(entity: GameEntity<out EntityType>, context: GameContext): Boolean {
        val (_, _, uiEvent, player) = context // world and screen ignored
        val currentPos = player.position
        logDevGameEvent("incoming uievent: $uiEvent with game state ${MetaContext.gameState}")
        if (MetaContext.gameState == GameState.PLAYER_TURN) {
            logDevGameEvent("player turn input handling")
            if (uiEvent is KeyboardEvent) {
                logDevGameEvent("player turn keyboard event")
                when (uiEvent.code) {
                    KeyCode.KEY_W -> player.moveTo(currentPos.withRelativeY(-1), context)
                    KeyCode.KEY_A -> player.moveTo(currentPos.withRelativeX(-1), context)
                    KeyCode.KEY_S -> player.moveTo(currentPos.withRelativeY(1), context)
                    KeyCode.KEY_D -> player.moveTo(currentPos.withRelativeX(1), context)
                    KeyCode.KEY_R -> player.moveUp(context)
                    KeyCode.KEY_F -> player.moveDown(context)
                    KeyCode.KEY_P -> player.pickItemUp(currentPos, context)
                    KeyCode.KEY_I -> player.inspectInventory(currentPos, context)
                    KeyCode.KEY_H -> showHelp(context.screen)
                    KeyCode.KEY_G -> player.healSelf(context)// TODO heal amount
                    KeyCode.KEY_Z -> player.zap(context)
                    else -> {
                        logger.debug("UI Event ($uiEvent) does not have a corresponding command, it is ignored.")
                    }
                }
            }
            if (MetaContext.gameState != GameState.TARGETING) {
                MetaContext.gameState = GameState.NPC_TURN
            }
        }

        if (MetaContext.gameState == GameState.TARGETING) {
            if (uiEvent is MouseEvent) {
                logDevGameEvent("ui mouse event while targeting: $uiEvent")
                when (uiEvent.button) {

                }
            }
            if (uiEvent is KeyboardEvent) {
                when (uiEvent.code) {
                    KeyCode.ESCAPE -> {
                        MetaContext.gameState = GameState.PLAYER_TURN
                        MetaContext.suspendedAction = null
                    }
                    else -> {
                        logDevGameEvent("UI Event ($uiEvent) does not have a corresponding command during targeting, it is ignored.")
                    }
                }
            }
        }
        return true
    }

    private fun showHelp(screen: Screen) {
        screen.openModal(HelpDialog(screen))
    }
}
