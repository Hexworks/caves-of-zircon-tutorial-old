package norn.systems

import norn.extensions.*
import norn.functions.logDevGameEvent
import org.hexworks.amethyst.api.base.BaseBehavior
import org.hexworks.amethyst.api.entity.EntityType
import norn.world.GameContext
import org.hexworks.zircon.api.uievent.KeyCode
import org.hexworks.zircon.api.uievent.KeyboardEvent
import norn.view.dialog.HelpDialog
import norn.world.GameState
import norn.world.MetaContext
import org.hexworks.cobalt.logging.api.LoggerFactory
import org.hexworks.zircon.api.screen.Screen

object InputReceiver : BaseBehavior<GameContext>() {

    private val logger = LoggerFactory.getLogger(this::class)

    override fun update(entity: GameEntity<out EntityType>, context: GameContext): Boolean {
        val (_, _, uiEvent, player) = context
        val currentPos = player.position
        logDevGameEvent(uiEvent.toString())
        if (uiEvent is KeyboardEvent && MetaContext.gameState == GameState.PLAYER_TURN) {
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
                KeyCode.KEY_Z -> player.zap(context) // TODO probably shouldn't zap self
                else -> {
                    logger.debug("UI Event ($uiEvent) does not have a corresponding command, it is ignored.")
                }
            }
        }
        return true
    }

    private fun showHelp(screen: Screen) {
        screen.openModal(HelpDialog(screen))
    }
}
