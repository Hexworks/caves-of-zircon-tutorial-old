package org.hexworks.cavesofzircon.extensions

import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cavesofzircon.world.GameContext

/**
 * Fits any [Entity] type we use.
 */
typealias AnyGameEntity = Entity<EntityType, GameContext>

/**
 * Specializes [Entity] with our [GameContext] type.
 */
typealias GameEntity<T> = Entity<T, GameContext>
