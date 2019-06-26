package org.hexworks.cavesofzircon.systems

import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cavesofzircon.commands.Destroy
import org.hexworks.cavesofzircon.commands.EntityDestroyed
import org.hexworks.cavesofzircon.extensions.GameCommand
import org.hexworks.cavesofzircon.functions.logGameEvent
import org.hexworks.cavesofzircon.world.GameContext

object Destructible : BaseFacet<GameContext>() {

    override fun executeCommand(command: GameCommand<out EntityType>) = command.responseWhenCommandIs(Destroy::class) { (context, destroyer, target, cause) ->
        context.world.removeEntity(target)
        destroyer.executeCommand(EntityDestroyed(context, target, destroyer))
        logGameEvent("$target dies $cause.")
        Consumed
    }
}
