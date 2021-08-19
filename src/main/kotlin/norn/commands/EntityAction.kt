package norn.commands

import org.hexworks.amethyst.api.entity.EntityType
import norn.extensions.GameCommand
import norn.extensions.GameEntity

interface EntityAction<S : EntityType, T : EntityType> : GameCommand<S> {

    var target: GameEntity<T>

    operator fun component1() = context
    operator fun component2() = source
    operator fun component3() = target
}
