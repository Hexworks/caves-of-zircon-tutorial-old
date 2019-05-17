package org.hexworks.cavesofzircon.commands

import org.hexworks.cavesofzircon.attributes.types.ItemHolder
import org.hexworks.cavesofzircon.extensions.GameCommand
import org.hexworks.cavesofzircon.extensions.GameItemHolder
import org.hexworks.cavesofzircon.world.GameContext
import org.hexworks.zircon.api.data.impl.Position3D

/**
 * A [GameCommand] representing [source] picking up an item at [position].
 */
data class PickItemUp(override val context: GameContext,
                      override val source: GameItemHolder,
                      val position: Position3D) : GameCommand<ItemHolder>
