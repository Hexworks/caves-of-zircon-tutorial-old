package norn.commands.spells

import norn.attributes.types.Interactable
import norn.commands.EntityAction
import norn.extensions.GameEntity
import norn.world.GameContext

abstract class InteractableTargetedAction<T : Interactable>(
    context: GameContext,
    override val source: GameEntity<T>,
    override var target: GameEntity<T>
) : EntityAction<T, T> {

    open fun visitInteractable(interactable: GameEntity<Interactable>) {}
}