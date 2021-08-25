package norn.extensions

import norn.attributes.types.*
import norn.world.GameContext
import org.hexworks.amethyst.api.Command
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType

/**
 * Fits any [Entity] type we use.
 */
typealias AnyGameEntity = Entity<EntityType, GameContext>

/**
 * Specializes [Entity] with our [GameContext] type.
 */
typealias GameEntity<T> = Entity<T, GameContext>

typealias GameEntityContainer<T> = GameEntity<ContainerEntity<T>>

/**
 * Specializes [Command] with our [GameContext] type.
 */
typealias GameCommand<T> = Command<T, GameContext>

typealias GameItem = GameEntity<Item>

typealias GameItemHolder = GameEntity<ItemHolder>

typealias GameSpellbookHolder = GameEntity<SpellbookHolder>

typealias GameCombatItem = GameEntity<CombatItem>

typealias GameEquipmentHolder = GameEntity<EquipmentHolder>