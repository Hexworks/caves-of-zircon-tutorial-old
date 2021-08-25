package norn.commands.spells

import norn.attributes.types.Combatant
import norn.attributes.types.EnergyUser
import norn.attributes.types.Interactable
import norn.attributes.types.Player
import norn.commands.EntityAction
import norn.extensions.AnyGameEntity
import norn.extensions.GameEntity
import norn.world.GameContext
import org.hexworks.amethyst.api.entity.Entity

abstract class InteractableTargetedAction<T : Interactable>(
    context: GameContext,
    override val source: GameEntity<T>,
    override var target: GameEntity<T>
) : EntityAction<T, T> {

    open fun visitInteractable(interactable: GameEntity<Interactable>) {}
}