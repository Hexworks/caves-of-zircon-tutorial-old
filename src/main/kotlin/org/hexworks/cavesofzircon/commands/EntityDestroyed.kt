package org.hexworks.cavesofzircon.commands

import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cavesofzircon.extensions.GameCommand
import org.hexworks.cavesofzircon.extensions.GameEntity
import org.hexworks.cavesofzircon.world.GameContext

data class EntityDestroyed(override val context: GameContext,
                           override val source: GameEntity<EntityType>,
                           val destroyer: GameEntity<EntityType>) : GameCommand<EntityType>
