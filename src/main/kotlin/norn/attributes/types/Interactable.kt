package norn.attributes.types

import norn.extensions.GameEntity
import norn.functions.logDevGameEvent
import norn.functions.logGameEvent
import norn.world.GameContext
import org.hexworks.amethyst.api.entity.EntityType

interface Interactable : EntityType {
    fun interact(context: GameContext,  source: GameEntity<EntityType>)
}
typealias InteractableEntity = GameEntity<Interactable>

fun InteractableEntity.interact(context: GameContext,  source: GameEntity<EntityType>) {
    logGameEvent("something happened")
}