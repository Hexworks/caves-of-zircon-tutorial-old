package norn.extensions

import norn.attributes.types.Combatant
import norn.attributes.types.Player
import norn.commands.*
import norn.commands.spells.Heal
import norn.commands.spells.Zap
import norn.functions.logDevGameEvent
import norn.world.GameContext
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

fun GameEntity<Player>.zap(context: GameContext, target: GameEntity<Combatant>) {
    logDevGameEvent("Firing zap executeCommand")
    executeCommand(Zap(context, this, target))
}