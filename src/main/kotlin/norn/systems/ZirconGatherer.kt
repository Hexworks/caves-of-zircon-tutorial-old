package norn.systems

import org.hexworks.amethyst.api.Command
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Pass
import org.hexworks.amethyst.api.Response
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType
import norn.attributes.ZirconCounter
import norn.attributes.types.Zircon
import norn.attributes.types.ZirconHolder
import norn.attributes.types.zirconCounter
import norn.commands.PickItemUp
import norn.extensions.whenTypeIs
import norn.functions.logGameEvent
import norn.world.GameContext
import org.hexworks.cobalt.datatypes.extensions.map

object ZirconGatherer : BaseFacet<GameContext>(ZirconCounter::class) {

    override fun executeCommand(command: Command<out EntityType, GameContext>) = command.responseWhenCommandIs(PickItemUp::class) {
        val (context, source, position) = it
        var response: Response = Pass
        val world = context.world
        world.findTopItem(position).map { item ->
            source.whenTypeIs<ZirconHolder> { zirconHolder ->
                if (item.type == Zircon) {
                    zirconHolder.zirconCounter.zirconCount++
                    world.removeEntity(item)
                    logGameEvent("$zirconHolder picked up a Zircon!")
                    response = Consumed
                }
            }
        }
        response
    }
}
