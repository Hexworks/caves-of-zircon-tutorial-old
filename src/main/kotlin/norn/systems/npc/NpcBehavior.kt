package norn.systems.npc

import norn.world.GameContext
import org.hexworks.amethyst.api.base.BaseBehavior
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType

object NpcBehavior : BaseBehavior<GameContext>() {
    override fun update(entity: Entity<EntityType, GameContext>, context: GameContext): Boolean {
        return false
    }
}