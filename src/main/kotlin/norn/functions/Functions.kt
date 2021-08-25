package norn.functions

import norn.GameConfig
import norn.RunMode
import norn.attributes.types.Interactable
import norn.attributes.types.InteractableEntity
import norn.events.GameLogEvent
import norn.extensions.AnyGameEntity
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cobalt.datatypes.Maybe
import org.hexworks.zircon.internal.Zircon

fun logGameEvent(text: String) {
    Zircon.eventBus.publish(GameLogEvent(text))
}


fun logDevGameEvent(text: String) {
    println(text)
}

fun logDebugGameEvent(text: String) {
    if (GameConfig.runMode == RunMode.DEBUG) {
        Zircon.eventBus.publish(GameLogEvent(text))
    }
}

/**
 * Casts this entity to [E] when it's type is [T] and provides it to [whenType]. Else return
 * [Maybe.empty].
 *
 * **This functions should not be used directly!** Instead every entity type/typealias should
 * have its own `asSomeEntity()` function!
 *
 * This function does a cast based on the entity type. **So make sure that [E] is an entity with [EntityType] [T]!**
 *
 * @param E the type this entity is cast to
 * @param T when this entity has this [EntityType] it will be cast without checking
 * @param R the return type of [whenType]
 * @param whenType invoked with this entity as parameter when the type matches [T]
 */
internal inline fun <E : AnyGameEntity, reified T : EntityType, R> AnyGameEntity.tryCastTo(whenType: (E) -> R): Maybe<R> =
    if (type is T) {
        @Suppress("UNCHECKED_CAST")
        val result = whenType(this as E)
        Maybe.of(result)
    } else {
        Maybe.empty()
    }

fun <R> AnyGameEntity.asInteractableEntity(whenInteracting: (InteractableEntity) -> R): Maybe<R> =
    tryCastTo<InteractableEntity, Interactable, R>(whenInteracting)