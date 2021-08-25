package norn.extensions

import norn.attributes.types.*
import norn.builders.EntityFactory.newPlaceholder
import norn.commands.*
import norn.commands.spells.CombatantTargetedSpellAction
import norn.commands.spells.Fireball
import norn.commands.spells.Heal
import norn.commands.spells.Zap
import norn.functions.logDevGameEvent
import norn.functions.logGameEvent
import norn.systems.Interactor
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

// TODO: abstract away self targeting spells
fun GameEntity<Player>.healSelf(
    context: GameContext
) {
    executeCommand(Heal(context, this, this))
}

fun GameEntity<Player>.zap(context: GameContext) {
    // set context, then let inputreceiver handle the click and cancelling
    MetaContext.gameState = GameState.TARGETING
    MetaContext.suspendedAction = Zap(context, this, newPlaceholder())
}

fun GameEntity<Player>.fireball(context: GameContext) {
    // set context, then let inputreceiver handle the click and cancelling
    MetaContext.gameState = GameState.TARGETING
    MetaContext.suspendedAction = Fireball(context, this, newPlaceholder())
}

fun GameEntity<Player>.doTargetedAction(context: GameContext,
                                        action: CombatantTargetedSpellAction<EnergyUser, Combatant>,
                                        target: GameEntity<Combatant>) {
    action.target = target
    executeCommand(action)
}

fun GameEntity<Player>.interact(context: GameContext) {
    val maybeEntity = context.world.findEntityNear<Interactable>(context.player.position)
    if (maybeEntity.isEmpty() ){
        logGameEvent("There is no interactable entity there.")
        return
    }
    executeCommand(Interact(context, this, maybeEntity.get()))
}