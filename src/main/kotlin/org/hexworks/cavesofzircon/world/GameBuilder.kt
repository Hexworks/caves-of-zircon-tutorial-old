package org.hexworks.cavesofzircon.world

import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cavesofzircon.GameConfig
import org.hexworks.cavesofzircon.GameConfig.BATS_PER_LEVEL
import org.hexworks.cavesofzircon.GameConfig.WORLD_SIZE
import org.hexworks.cavesofzircon.attributes.types.Player
import org.hexworks.cavesofzircon.builders.EntityFactory
import org.hexworks.cavesofzircon.extensions.GameEntity
import org.hexworks.zircon.api.Positions
import org.hexworks.zircon.api.Sizes
import org.hexworks.zircon.api.data.Size
import org.hexworks.zircon.api.data.impl.Size3D

class GameBuilder(val worldSize: Size3D = WORLD_SIZE) {

    private val visibleSize = Sizes.create3DSize(
            xLength = GameConfig.WINDOW_WIDTH - GameConfig.SIDEBAR_WIDTH,
            yLength = GameConfig.WINDOW_HEIGHT - GameConfig.LOG_AREA_HEIGHT,
            zLength = 1)

    val world = WorldBuilder(worldSize)
            .makeCaves()
            .build(visibleSize = visibleSize)

    fun buildGame(): Game {
        prepareWorld()
        addFungi()
        addBats()
        addZircons()
        addZombies()
        addExit()
        return Game.create(world)
    }

    private fun prepareWorld() = also {
        world.scrollUpBy(world.actualSize().zLength)
    }

    private fun addPlayer(): GameEntity<Player> {
        return EntityFactory.newPlayer().addToWorld(
                atLevel = GameConfig.DUNGEON_LEVELS - 1,
                atArea = world.visibleSize().to2DSize())
    }

    private fun addFungi() = also {
        repeat(world.actualSize().zLength) { level ->
            repeat(GameConfig.FUNGI_PER_LEVEL) {
                EntityFactory.newFungus().addToWorld(level)
            }
        }
    }

    private fun addZircons() = also {
        repeat(world.actualSize().zLength) { level ->
            repeat(GameConfig.ZIRCONS_PER_LEVEL) {
                EntityFactory.newZircon().addToWorld(level)
            }
        }
    }

    private fun addBats() = also {
        repeat(world.actualSize().zLength) { level ->
            repeat(BATS_PER_LEVEL) {
                EntityFactory.newBat().addToWorld(level)
            }
        }
    }

    private fun addZombies() = also {
        repeat(world.actualSize().zLength) { level ->
            repeat(GameConfig.ZOMBIES_PER_LEVEL) {
                EntityFactory.newZombie().addToWorld(level)
            }
        }
    }

    private fun addExit() = also {
        EntityFactory.newExit().addToWorld(0)
    }

    private fun <T : EntityType> GameEntity<T>.addToWorld(
            atLevel: Int,
            atArea: Size = world.actualSize().to2DSize()): GameEntity<T> {
        world.addAtEmptyPosition(this,
                offset = Positions.default3DPosition().withZ(atLevel),
                size = Size3D.from2DSize(atArea))
        return this
    }

    companion object {

        fun defaultGame() = GameBuilder(
                worldSize = GameConfig.WORLD_SIZE).buildGame()
    }
}
