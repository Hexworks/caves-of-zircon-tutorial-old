package norn.attributes.types

import norn.extensions.GameEntity
import norn.world.GameContext
import org.hexworks.amethyst.api.entity.EntityType

interface Interactable : EntityType {
    fun interact(context: GameContext,  source: GameEntity<EntityType>)
}
typealias InteractableEntity = GameEntity<Interactable>
