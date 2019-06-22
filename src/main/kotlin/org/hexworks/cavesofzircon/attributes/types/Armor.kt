package org.hexworks.cavesofzircon.attributes.types

import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.cavesofzircon.attributes.ItemCombatStats
import org.hexworks.cavesofzircon.world.GameContext

interface Armor : CombatItem

val Entity<Armor, GameContext>.attackValue: Int
    get() = findAttribute(ItemCombatStats::class).get().attackValue

val Entity<Armor, GameContext>.defenseValue: Int
    get() = findAttribute(ItemCombatStats::class).get().defenseValue
