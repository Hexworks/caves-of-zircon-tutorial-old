package org.hexworks.cavesofzircon.systems

import org.hexworks.amethyst.api.base.BaseBehavior
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cavesofzircon.attributes.types.Player
import org.hexworks.cavesofzircon.commands.InspectInventory
import org.hexworks.cavesofzircon.commands.MoveDown
import org.hexworks.cavesofzircon.commands.MoveTo
import org.hexworks.cavesofzircon.commands.MoveUp
import org.hexworks.cavesofzircon.commands.PickItemUp
import org.hexworks.cavesofzircon.extensions.GameEntity
import org.hexworks.cavesofzircon.extensions.position
import org.hexworks.cavesofzircon.view.dialog.HelpDialog
import org.hexworks.cavesofzircon.world.GameContext
import org.hexworks.cobalt.logging.api.LoggerFactory
import org.hexworks.zircon.api.data.impl.Position3D
import org.hexworks.zircon.api.screen.Screen
import org.hexworks.zircon.api.uievent.KeyCode
import org.hexworks.zircon.api.uievent.KeyboardEvent

object InputReceiver : BaseBehavior<GameContext>() {

    private val logger = LoggerFactory.getLogger(this::class)

    override fun update(entity: GameEntity<out EntityType>, context: GameContext): Boolean {
        val (_, _, uiEvent, player) = context
        val currentPos = player.position
        if (uiEvent is KeyboardEvent) {
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
                else -> {
                    logger.debug("UI Event ($uiEvent) does not have a corresponding command, it is ignored.")
                }
            }
        }
        return true
    }

    private fun GameEntity<Player>.inspectInventory(position: Position3D, context: GameContext) {
        executeCommand(InspectInventory(context, this, position))
    }

    private fun showHelp(screen: Screen) {
        screen.openModal(HelpDialog(screen))
    }

    private fun GameEntity<Player>.pickItemUp(position: Position3D, context: GameContext) {
        executeCommand(PickItemUp(context, this, position))
    }

    private fun GameEntity<Player>.moveTo(position: Position3D, context: GameContext) {
        executeCommand(MoveTo(context, this, position))
    }

    private fun GameEntity<Player>.moveUp(context: GameContext) {
        executeCommand(MoveUp(context, this))
    }

    private fun GameEntity<Player>.moveDown(context: GameContext) {
        executeCommand(MoveDown(context, this))
    }
}
