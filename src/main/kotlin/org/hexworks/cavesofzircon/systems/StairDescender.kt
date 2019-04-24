package org.hexworks.cavesofzircon.systems

import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cavesofzircon.attributes.types.StairsDown
import org.hexworks.cavesofzircon.blocks.GameBlock
import org.hexworks.cavesofzircon.commands.MoveDown
import org.hexworks.cavesofzircon.extensions.GameCommand
import org.hexworks.cavesofzircon.extensions.position
import org.hexworks.cavesofzircon.functions.logGameEvent
import org.hexworks.cavesofzircon.world.GameContext
import org.hexworks.cobalt.datatypes.extensions.map

object StairDescender : BaseFacet<GameContext>() {

    override fun executeCommand(command: GameCommand<out EntityType>) = command.responseWhenCommandIs(MoveDown::class) { (context, player) ->
        val world = context.world
        val playerPos = player.position
        world.fetchBlockAt(playerPos).map { block ->
            if (block.hasStairsDown) {
                logGameEvent("You move down one level...")
                world.moveEntity(player, playerPos.withRelativeZ(-1))
                world.scrollOneDown()
            } else {
                logGameEvent("You search for a trapdoor, but you find nothing.")
            }
        }
        Consumed
    }

    private val GameBlock.hasStairsDown: Boolean
        get() = this.entities.any { it.type == StairsDown }
}
