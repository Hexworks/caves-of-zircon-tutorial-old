package norn.commands

import norn.attributes.types.Interactable
import norn.attributes.types.InteractableEntity
import norn.extensions.GameEntity

import norn.world.GameContext
import org.hexworks.amethyst.api.entity.EntityType

data class Interact(
    override val context: GameContext,
    override val source: GameEntity<EntityType>,
    override val target: InteractableEntity
) : EntityAction<EntityType, Interactable>