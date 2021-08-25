package norn.commands

import norn.attributes.types.Combatant
import norn.attributes.types.Interactor
import norn.extensions.GameEntity
import norn.world.GameContext

data class Interact(
    override val context: GameContext,
    override val source: GameEntity<Interactor>,
    override var target: GameEntity<Interactor>
) : EntityAction<Interactor, Interactor>