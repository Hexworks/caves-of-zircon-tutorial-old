package norn.systems

import norn.commands.MoveCamera
import norn.extensions.GameCommand
import norn.extensions.position
import norn.world.GameContext
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType

object CameraMover : BaseFacet<GameContext>() {

    override fun executeCommand(command: GameCommand<out EntityType>) =
        command.responseWhenCommandIs(MoveCamera::class) { cmd ->
            val (context, source, previousPosition) = cmd
            val world = context.world
            val screenPos = source.position - world.visibleOffset()     // 1
            val halfHeight = world.visibleSize().yLength / 2            // 2
            val halfWidth = world.visibleSize().xLength / 2             // 3
            val currentPosition = source.position
            when {                                                      // 4
                previousPosition.y > currentPosition.y && screenPos.y < halfHeight -> {
                    world.scrollOneBackward()
                }
                previousPosition.y < currentPosition.y && screenPos.y > halfHeight -> {
                    world.scrollOneForward()
                }
                previousPosition.x > currentPosition.x && screenPos.x < halfWidth -> {
                    world.scrollOneLeft()
                }
                previousPosition.x < currentPosition.x && screenPos.x > halfWidth -> {
                    world.scrollOneRight()
                }
            }
            Consumed
        }
}
