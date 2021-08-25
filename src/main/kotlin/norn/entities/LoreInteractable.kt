package norn.entities

import norn.attributes.types.Interactable
import norn.extensions.GameEntity
import norn.functions.logGameEvent
import norn.world.GameContext
import org.hexworks.amethyst.api.entity.EntityType

open class LoreInteractable(override val description: String, override val name: String) : Interactable {

    var text = "You get nothing from this."

    override fun interact(context: GameContext, source: GameEntity<EntityType>) {
        logGameEvent("${this.description} It reads: $text")
    }
}