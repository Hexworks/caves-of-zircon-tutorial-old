package org.hexworks.cavesofzircon.commands

import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cavesofzircon.extensions.GameEntity
import org.hexworks.cavesofzircon.world.GameContext

data class Dig(override val context: GameContext,
               override val source: GameEntity<EntityType>,
               override val target: GameEntity<EntityType>) : EntityAction<EntityType, EntityType>
