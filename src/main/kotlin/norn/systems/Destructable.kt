package norn.systems

import norn.commands.Destroy
import norn.commands.EntityDestroyed
import norn.events.PlayerDied
import norn.extensions.GameCommand
import norn.extensions.isPlayer
import norn.functions.logGameEvent
import norn.world.GameContext
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.internal.Zircon

object Destructible : BaseFacet<GameContext>() {

    override fun executeCommand(command: GameCommand<out EntityType>) =
        command.responseWhenCommandIs(Destroy::class) { (context, destroyer, target, cause) ->
            context.world.removeEntity(target)
            destroyer.executeCommand(EntityDestroyed(context, target, destroyer))
            if (target.isPlayer) {
                Zircon.eventBus.publish(PlayerDied("You died $cause"))
            }
            logGameEvent("$target dies $cause.")
            Consumed
        }
}