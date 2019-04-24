package org.hexworks.cavesofzircon.systems

import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cavesofzircon.attributes.types.StairsUp
import org.hexworks.cavesofzircon.blocks.GameBlock
import org.hexworks.cavesofzircon.commands.MoveUp
import org.hexworks.cavesofzircon.extensions.GameCommand
import org.hexworks.cavesofzircon.extensions.position
import org.hexworks.cavesofzircon.functions.logGameEvent
import org.hexworks.cavesofzircon.world.GameContext
import org.hexworks.cobalt.datatypes.extensions.map

object StairClimber : BaseFacet<GameContext>() {

    override fun executeCommand(command: GameCommand<out EntityType>) = command.responseWhenCommandIs(MoveUp::class) { (context, player) ->
        val world = context.world
        val playerPos = player.position
        world.fetchBlockAt(playerPos).map { block ->
            if (block.hasStairsUp) {
                logGameEvent("You move up one level...")
                world.moveEntity(player, playerPos.withRelativeZ(1))
                world.scrollOneUp()
            } else {
                logGameEvent("You jump up and try to reach the ceiling. You fail.")
            }
        }
        Consumed
    }

    private val GameBlock.hasStairsUp: Boolean
        get() = this.entities.any { it.type == StairsUp }
}
