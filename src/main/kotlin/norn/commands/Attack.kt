package norn.commands

import norn.attributes.types.Combatant
import norn.extensions.GameEntity
import norn.world.GameContext

data class Attack(
    override val context: GameContext,
    override val source: GameEntity<Combatant>,
    override var target: GameEntity<Combatant>
) : EntityAction<Combatant, Combatant>