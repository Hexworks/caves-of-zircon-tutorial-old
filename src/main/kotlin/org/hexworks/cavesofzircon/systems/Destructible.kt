package org.hexworks.cavesofzircon.systems

import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cavesofzircon.commands.Destroy
import org.hexworks.cavesofzircon.commands.EntityDestroyed
import org.hexworks.cavesofzircon.events.PlayerDied
import org.hexworks.cavesofzircon.extensions.GameCommand
import org.hexworks.cavesofzircon.extensions.isPlayer
import org.hexworks.cavesofzircon.functions.logGameEvent
import org.hexworks.cavesofzircon.world.GameContext
import org.hexworks.zircon.internal.Zircon

object Destructible : BaseFacet<GameContext>() {

    override fun executeCommand(command: GameCommand<out EntityType>) = command.responseWhenCommandIs(Destroy::class) { (context, destroyer, target, cause) ->
        context.world.removeEntity(target)
        destroyer.executeCommand(EntityDestroyed(context, target, destroyer))
        if (target.isPlayer) {
            Zircon.eventBus.publish(PlayerDied("You died $cause"))
        }
        logGameEvent("$target dies $cause.")
        Consumed
    }
}
