package org.hexworks.cavesofzircon.attributes.types

import org.hexworks.cavesofzircon.attributes.ItemCombatStats
import org.hexworks.cavesofzircon.extensions.GameEntity

interface Weapon : CombatItem

val GameEntity<Weapon>.attackValue: Int
    get() = findAttribute(ItemCombatStats::class).get().attackValue

val GameEntity<Weapon>.defenseValue: Int
    get() = findAttribute(ItemCombatStats::class).get().defenseValue
