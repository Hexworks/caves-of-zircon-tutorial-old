package norn.attributes.types

import org.hexworks.amethyst.api.entity.EntityType
import norn.attributes.CombatStats
import norn.attributes.Experience
import norn.extensions.GameEntity

interface ExperienceGainer : EntityType

val GameEntity<ExperienceGainer>.experience: Experience
    get() = findAttribute(Experience::class).get()

val GameEntity<ExperienceGainer>.combatStats: CombatStats
    get() = findAttribute(CombatStats::class).get()

