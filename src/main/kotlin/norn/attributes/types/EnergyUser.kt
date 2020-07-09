package norn.attributes.types

import org.hexworks.amethyst.api.entity.EntityType
import norn.attributes.EnergyLevel
import norn.extensions.GameEntity

interface EnergyUser : EntityType

val GameEntity<EnergyUser>.energyLevel: EnergyLevel
    get() = findAttribute(EnergyLevel::class).get()
