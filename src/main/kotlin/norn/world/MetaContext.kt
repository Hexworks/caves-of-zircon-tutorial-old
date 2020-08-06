package norn.world

import norn.RunMode
import norn.commands.EntityAction
import org.hexworks.amethyst.api.entity.EntityType

object MetaContext {
    var runMode: RunMode = RunMode.PLAYER
    var gameState: GameState = GameState.PREGAME
    var suspendedAction: EntityAction<EntityType, EntityType>? = null
}