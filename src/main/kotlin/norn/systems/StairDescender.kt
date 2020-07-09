package norn.systems

import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType
import norn.attributes.types.Exit
import norn.attributes.types.Player
import norn.attributes.types.StairsDown
import norn.attributes.types.zirconCounter
import norn.blocks.GameBlock
import norn.commands.MoveDown
import norn.events.PlayerWonTheGame
import norn.extensions.GameCommand
import norn.extensions.position
import norn.extensions.whenTypeIs
import norn.functions.logGameEvent
import norn.world.GameContext
import org.hexworks.cobalt.datatypes.extensions.map
import org.hexworks.zircon.internal.Zircon

object StairDescender : BaseFacet<GameContext>() {

    override fun executeCommand(command: GameCommand<out EntityType>) = command.responseWhenCommandIs(MoveDown::class) { (context, source) ->
        val world = context.world
        val pos = source.position
        world.fetchBlockAt(pos).map { block ->
            when {
                block.hasStairsDown -> {
                    logGameEvent("You move down one level...")
                    world.moveEntity(source, pos.withRelativeZ(-1))
                    world.scrollOneDown()
                }
                block.hasExit -> source.whenTypeIs<Player> {
                    Zircon.eventBus.publish(PlayerWonTheGame(it.zirconCounter.zirconCount))
                }
                else -> logGameEvent("You search for a trapdoor, but you find nothing.")
            }
        }
        Consumed
    }

    private val GameBlock.hasStairsDown: Boolean
        get() = this.entities.any { it.type == StairsDown }

    private val GameBlock.hasExit: Boolean
        get() = this.entities.any { it.type == Exit }
}
