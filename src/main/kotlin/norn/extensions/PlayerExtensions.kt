package norn.extensions

import norn.attributes.types.Combatant
import norn.attributes.types.Player
import norn.commands.*
import norn.commands.spells.Heal
import norn.commands.spells.Zap
import norn.functions.logDevGameEvent
import norn.world.GameContext
import norn.world.GameState
import norn.world.MetaContext
import org.hexworks.zircon.api.data.impl.Position3D

fun GameEntity<Player>.moveTo(position: Position3D, context: GameContext) {
    executeCommand(MoveTo(context, this, position))
}

fun GameEntity<Player>.moveUp(context: GameContext) {
    executeCommand(MoveUp(context, this))
}

fun GameEntity<Player>.moveDown(context: GameContext) {
    executeCommand(MoveDown(context, this))
}

fun GameEntity<Player>.pickItemUp(position: Position3D, context: GameContext) {
    executeCommand(PickItemUp(context, this, position))
}

fun GameEntity<Player>.inspectInventory(position: Position3D, context: GameContext) {
    executeCommand(InspectInventory(context, this, position))
}

fun GameEntity<Player>.healSelf(context: GameContext
        //source: GameEntity<EnergyUser>,
        //target: GameEntity<Combatant>,
                                        ) {
    logDevGameEvent("Firing heal executeCommand")
    executeCommand(Heal(context, this, this))
}

fun GameEntity<Player>.zap(context: GameContext) {
    logDevGameEvent("Firing zap executeCommand")
    // set context, then let inputreceiver handle the click and cancelling
    MetaContext.gameState = GameState.TARGETING
    MetaContext.suspendedAction = Zap(context, this)
}