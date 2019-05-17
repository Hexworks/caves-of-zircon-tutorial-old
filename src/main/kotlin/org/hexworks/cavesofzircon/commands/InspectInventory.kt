package org.hexworks.cavesofzircon.commands

import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cavesofzircon.extensions.GameCommand
import org.hexworks.cavesofzircon.extensions.GameItemHolder
import org.hexworks.cavesofzircon.world.GameContext
import org.hexworks.zircon.api.data.impl.Position3D

data class InspectInventory(override val context: GameContext,
                            override val source: GameItemHolder,
                            val position: Position3D) : GameCommand<EntityType>
