package org.hexworks.cavesofzircon.attributes.types

import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cavesofzircon.attributes.EnergyLevel
import org.hexworks.cavesofzircon.extensions.GameEntity

interface EnergyUser : EntityType

val GameEntity<EnergyUser>.energyLevel: EnergyLevel
    get() = findAttribute(EnergyLevel::class).get()
