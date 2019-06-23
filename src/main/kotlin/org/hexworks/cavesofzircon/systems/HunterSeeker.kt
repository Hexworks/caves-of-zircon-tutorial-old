package org.hexworks.cavesofzircon.systems

import org.hexworks.amethyst.api.base.BaseBehavior
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cavesofzircon.commands.MoveTo
import org.hexworks.cavesofzircon.extensions.GameEntity
import org.hexworks.cavesofzircon.extensions.position
import org.hexworks.cavesofzircon.world.GameContext
import org.hexworks.zircon.api.Positions

object HunterSeeker : BaseBehavior<GameContext>() {

    override fun update(entity: GameEntity<out EntityType>, context: GameContext): Boolean {
        val (world, _, _, player) = context
        var hunted = false
        world.whenCanSee(entity, player) { path ->
            entity.executeCommand(MoveTo(
                    context = context,
                    source = entity,
                    position = Positions.from2DTo3D(path.iterator().next(), player.position.z)))
            hunted = true
        }
        return hunted
    }
}
