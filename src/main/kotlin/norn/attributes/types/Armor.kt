package norn.attributes.types

import org.hexworks.amethyst.api.entity.Entity
import norn.attributes.ItemCombatStats
import norn.world.GameContext

interface Armor : CombatItem

val Entity<Armor, GameContext>.attackValue: Int
    get() = findAttribute(ItemCombatStats::class).get().attackValue

val Entity<Armor, GameContext>.defenseValue: Int
    get() = findAttribute(ItemCombatStats::class).get().defenseValue
