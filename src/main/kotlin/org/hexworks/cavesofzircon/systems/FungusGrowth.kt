package org.hexworks.cavesofzircon.systems

import org.hexworks.amethyst.api.base.BaseBehavior
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cavesofzircon.attributes.FungusSpread
import org.hexworks.cavesofzircon.builders.EntityFactory
import org.hexworks.cavesofzircon.extensions.GameEntity
import org.hexworks.cavesofzircon.extensions.position
import org.hexworks.cavesofzircon.extensions.tryToFindAttribute
import org.hexworks.cavesofzircon.world.GameContext
import org.hexworks.cobalt.datatypes.extensions.map
import org.hexworks.zircon.api.Sizes

object FungusGrowth : BaseBehavior<GameContext>(FungusSpread::class) {

    override fun update(entity: GameEntity<out EntityType>, context: GameContext): Boolean {
        val world = context.world
        val fungusSpread = entity.tryToFindAttribute(FungusSpread::class)
        val (spreadCount, maxSpread) = fungusSpread
        return if (spreadCount < maxSpread && Math.random() < 0.015) {
            world.findEmptyLocationWithin(
                    offset = entity.position
                            .withRelativeX(-1)
                            .withRelativeY(-1),
                    size = Sizes.create3DSize(3, 3, 0)).map { emptyLocation ->
                world.addEntity(EntityFactory.newFungus(fungusSpread), emptyLocation)
                fungusSpread.spreadCount++
            }
            true
        } else false
    }
}
