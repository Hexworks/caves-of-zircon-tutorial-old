package norn.attributes.types

import org.hexworks.amethyst.api.entity.EntityType
import norn.attributes.CombatStats
import norn.commands.spells.CombatantTargetedSpellAction
import norn.extensions.GameEntity

interface Combatant : EntityType

val GameEntity<Combatant>.combatStats: CombatStats
    get() = findAttribute(CombatStats::class).get()

fun GameEntity<Combatant>.heal(amount: Int) {
    if (combatStats.hp + amount > combatStats.maxHp) {
        combatStats.hp = combatStats.maxHp
    } else {
        combatStats.hp += amount
    }

    }

fun GameEntity<Combatant>.acceptVisitor(visitor: CombatantTargetedSpellAction<EnergyUser, Combatant>) {
    visitor.visitCombatant(this)
}