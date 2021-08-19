package norn.systems

import norn.attributes.types.StairsUp
import norn.blocks.GameBlock
import norn.commands.MoveUp
import norn.extensions.GameCommand
import norn.extensions.position
import norn.functions.logGameEvent
import norn.world.GameContext
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cobalt.datatypes.extensions.map

object StairClimber : BaseFacet<GameContext>() {

    override fun executeCommand(command: GameCommand<out EntityType>) =
        command.responseWhenCommandIs(MoveUp::class) { (context, player) ->
            val world = context.world
            val playerPos = player.position
            world.fetchBlockAt(playerPos).map { block ->
                if (block.hasStairsUp) {
                    world.moveEntity(player, playerPos.withRelativeZ(1))
                    world.scrollOneUp()
                    logGameEvent("You move up one level...")
                } else {
                    logGameEvent("You jump up and try to reach the ceiling. You fail.")
                }
            }
            Consumed
        }

    private val GameBlock.hasStairsUp: Boolean
        get() = this.entities.any { it.type == StairsUp }
}
