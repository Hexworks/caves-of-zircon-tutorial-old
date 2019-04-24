package org.hexworks.cavesofzircon.commands

import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cavesofzircon.extensions.GameCommand
import org.hexworks.cavesofzircon.extensions.GameEntity
import org.hexworks.cavesofzircon.world.GameContext

/**
 * A [GameCommand] representing moving [source] up.
 */
data class MoveUp(override val context: GameContext,
                  override val source: GameEntity<EntityType>) : GameCommand<EntityType>
