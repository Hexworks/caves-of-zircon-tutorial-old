package norn.attributes.types

import norn.commands.Interact
import norn.extensions.GameEntity
import norn.functions.logGameEvent
import norn.world.GameContext
import org.hexworks.amethyst.api.entity.EntityType

interface InteractableEntity : EntityType

fun GameEntity<InteractableEntity>.doInteraction(context: GameContext, source: GameEntity<EntityType>) {
    logGameEvent("$this.name stares back at you.")
}

