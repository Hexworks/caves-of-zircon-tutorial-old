package norn.attributes.types

import norn.attributes.NutritionalValue
import norn.extensions.GameEntity
import norn.extensions.tryToFindAttribute

interface Food : Item

val GameEntity<Food>.energy: Int
    get() = tryToFindAttribute(NutritionalValue::class).energy
