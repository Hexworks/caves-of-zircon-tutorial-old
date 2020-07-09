package norn.attributes.types

import org.hexworks.amethyst.api.entity.EntityType
import norn.attributes.CombatStats
import norn.extensions.GameEntity

interface Combatant : EntityType

val GameEntity<Combatant>.combatStats: CombatStats
    get() = findAttribute(CombatStats::class).get()
