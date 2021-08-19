package norn.world

import norn.RunMode
import norn.attributes.types.Combatant
import norn.attributes.types.EnergyUser
import norn.commands.EntityAction
import org.hexworks.amethyst.api.Command
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cobalt.databinding.api.createPropertyFrom
import org.hexworks.cobalt.databinding.api.property.Property

object MetaContext {

    var gameStateProperty: Property<GameState> = createPropertyFrom(GameState.PREGAME)
    var gameState: GameState by gameStateProperty.asDelegate()

    var runMode: RunMode = RunMode.PLAYER
    var suspendedAction: Command<out EntityType, GameContext>? = null
}