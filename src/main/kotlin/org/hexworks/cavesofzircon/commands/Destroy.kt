package org.hexworks.cavesofzircon.commands

import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cavesofzircon.extensions.GameCommand
import org.hexworks.cavesofzircon.extensions.GameEntity
import org.hexworks.cavesofzircon.world.GameContext

data class Destroy(override val context: GameContext,
                   override val source: GameEntity<EntityType>,
                   val target: GameEntity<EntityType>,
                   val cause: String = "natural causes.") : GameCommand<EntityType>
