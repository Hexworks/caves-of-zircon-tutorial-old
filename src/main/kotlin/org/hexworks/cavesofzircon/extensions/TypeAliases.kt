package org.hexworks.cavesofzircon.extensions

import org.hexworks.amethyst.api.Command
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cavesofzircon.attributes.types.CombatItem
import org.hexworks.cavesofzircon.attributes.types.EquipmentHolder
import org.hexworks.cavesofzircon.attributes.types.Item
import org.hexworks.cavesofzircon.attributes.types.ItemHolder
import org.hexworks.cavesofzircon.world.GameContext

/**
 * Fits any [Entity] type we use.
 */
typealias AnyGameEntity = Entity<EntityType, GameContext>

/**
 * Specializes [Entity] with our [GameContext] type.
 */
typealias GameEntity<T> = Entity<T, GameContext>

/**
 * Specializes [Command] with our [GameContext] type.
 */
typealias GameCommand<T> = Command<T, GameContext>

typealias GameItem = GameEntity<Item>

typealias GameItemHolder = GameEntity<ItemHolder>

typealias GameCombatItem = GameEntity<CombatItem>

typealias GameEquipmentHolder = GameEntity<EquipmentHolder>
