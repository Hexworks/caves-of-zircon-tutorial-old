@file:Suppress("UNCHECKED_CAST")

package org.hexworks.cavesofzircon.extensions

import org.hexworks.amethyst.api.Attribute
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Pass
import org.hexworks.amethyst.api.Response
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cavesofzircon.attributes.CombatStats
import org.hexworks.cavesofzircon.attributes.EntityActions
import org.hexworks.cavesofzircon.attributes.EntityPosition
import org.hexworks.cavesofzircon.attributes.EntityTile
import org.hexworks.cavesofzircon.attributes.Equipment
import org.hexworks.cavesofzircon.attributes.ItemCombatStats
import org.hexworks.cavesofzircon.attributes.flags.BlockOccupier
import org.hexworks.cavesofzircon.attributes.flags.VisionBlocker
import org.hexworks.cavesofzircon.attributes.types.Combatant
import org.hexworks.cavesofzircon.attributes.types.Player
import org.hexworks.cavesofzircon.attributes.types.combatStats
import org.hexworks.cavesofzircon.world.GameContext
import org.hexworks.cobalt.datatypes.extensions.map
import org.hexworks.cobalt.datatypes.extensions.orElseThrow
import org.hexworks.zircon.api.data.Tile
import kotlin.reflect.KClass
import kotlin.reflect.full.isSuperclassOf

var AnyGameEntity.position
    get() = tryToFindAttribute(EntityPosition::class).position
    set(value) {
        findAttribute(EntityPosition::class).map {
            it.position = value
        }
    }

val AnyGameEntity.occupiesBlock: Boolean
    get() = findAttribute(BlockOccupier::class).isPresent

val AnyGameEntity.tile: Tile
    get() = this.tryToFindAttribute(EntityTile::class).tile

val AnyGameEntity.isPlayer: Boolean
    get() = this.type == Player

inline fun <reified T : EntityType> Iterable<AnyGameEntity>.filterType(): List<Entity<T, GameContext>> {
    return filter { T::class.isSuperclassOf(it.type::class) }.toList() as List<Entity<T, GameContext>>
}

inline fun <reified T : EntityType> AnyGameEntity.whenTypeIs(fn: (Entity<T, GameContext>) -> Unit) {
    if (T::class.isSuperclassOf(this.type::class)) {
        fn(this as Entity<T, GameContext>)
    }
}

val AnyGameEntity.attackValue: Int
    get() {
        val combat = findAttribute(CombatStats::class).map { it.attackValue }.orElse(0)
        val equipment = findAttribute(Equipment::class).map { it.attackValue }.orElse(0)
        val item = findAttribute(ItemCombatStats::class).map { it.attackValue }.orElse(0)
        return combat + equipment + item
    }

val AnyGameEntity.defenseValue: Int
    get() {
        val combat = findAttribute(CombatStats::class).map { it.defenseValue }.orElse(0)
        val equipment = findAttribute(Equipment::class).map { it.defenseValue }.orElse(0)
        val item = findAttribute(ItemCombatStats::class).map { it.defenseValue }.orElse(0)
        return combat + equipment + item
    }

val AnyGameEntity.blocksVision: Boolean
    get() = this.findAttribute(VisionBlocker::class).isPresent

fun GameEntity<Combatant>.whenHasNoHealthLeft(fn: () -> Unit) {
    if (combatStats.hp <= 0) {
        fn()
    }
}

fun <T : Attribute> AnyGameEntity.tryToFindAttribute(klass: KClass<T>): T = findAttribute(klass).orElseThrow {
    NoSuchElementException("Entity '$this' has no property with type '${klass.simpleName}'.")
}

fun AnyGameEntity.tryActionsOn(context: GameContext, target: AnyGameEntity): Response {
    var result: Response = Pass
    findAttribute(EntityActions::class).map {
        it.createActionsFor(context, this, target).forEach { action ->
            if (target.executeCommand(action) is Consumed) {
                result = Consumed
                return@forEach
            }
        }
    }
    return result
}
