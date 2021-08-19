package norn.attributes.types

import norn.attributes.ItemCombatStats
import norn.world.GameContext
import org.hexworks.amethyst.api.entity.Entity

interface Armor : CombatItem

val Entity<Armor, GameContext>.attackValue: Int
    get() = findAttribute(ItemCombatStats::class).get().attackValue

val Entity<Armor, GameContext>.defenseValue: Int
    get() = findAttribute(ItemCombatStats::class).get().defenseValue
