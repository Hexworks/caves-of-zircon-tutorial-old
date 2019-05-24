package org.hexworks.cavesofzircon.attributes.types

import org.hexworks.cavesofzircon.attributes.NutritionalValue
import org.hexworks.cavesofzircon.extensions.GameEntity
import org.hexworks.cavesofzircon.extensions.tryToFindAttribute

interface Food : Item

val GameEntity<Food>.energy: Int
    get() = tryToFindAttribute(NutritionalValue::class).energy
