package norn.world

import norn.GameConfig
import norn.GameConfig.WORLD_SIZE
import norn.attributes.types.Player
import norn.builders.EntityFactory
import norn.extensions.GameEntity
import org.hexworks.zircon.api.Positions
import org.hexworks.zircon.api.Sizes
import org.hexworks.zircon.api.data.impl.Size3D
import org.hexworks.amethyst.api.entity.EntityType
import norn.GameConfig.BATS_PER_LEVEL
import norn.GameConfig.FUNGI_PER_LEVEL
import norn.GameConfig.ZOMBIES_PER_LEVEL
import org.hexworks.zircon.api.data.Size

class GameBuilder(val worldSize: Size3D = WORLD_SIZE) {

    private val visibleSize = Sizes.create3DSize(
            xLength =  GameConfig.WINDOW_WIDTH -  GameConfig.SIDEBAR_WIDTH,
            yLength =  GameConfig.WINDOW_HEIGHT -  GameConfig.LOG_AREA_HEIGHT,
            zLength = 1)

    val world = WorldBuilder(worldSize)
            .makeCaves()
            .build(visibleSize = visibleSize)

    fun buildGame(): Game {

        prepareWorld()

        val player = addPlayer()
        addFungi()
        addBats()
        addZircons()
        addZombies()
        addExit()

        val game = Game.create(
                player = player,
                world = world)

        world.addWorldEntity(EntityFactory.newFogOfWar(game))

        return game
    }

    private fun prepareWorld() = also {
        world.scrollUpBy(world.actualSize().zLength)
    }

    private fun addPlayer(): GameEntity<Player> {
        return EntityFactory.newPlayer().addToWorld(
                atLevel =  GameConfig.DUNGEON_LEVELS - 1,
                atArea = world.visibleSize().to2DSize())
    }

    private fun addFungi() = also {
        repeat(world.actualSize().zLength) { level ->
            repeat(FUNGI_PER_LEVEL) {
                EntityFactory.newFungus().addToWorld(level)
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

    private fun <T : EntityType> GameEntity<T>.addToWorld(
            atLevel: Int,
            atArea: Size = world.actualSize().to2DSize()): GameEntity<T> {
        world.addAtEmptyPosition(this,
                offset = Positions.default3DPosition().withZ(atLevel),
                size = Size3D.from2DSize(atArea))
        return this
    }

    private fun addZircons() = also {
        repeat(world.actualSize().zLength) { level ->
            repeat( GameConfig.ZIRCONS_PER_LEVEL) {
                EntityFactory.newZircon().addToWorld(level)
            }
        }
    }

    /* dropping from zombies instead
    private fun addWeapons() = also {
        repeat(world.actualSize().zLength) { level ->
            repeat(WEAPONS_PER_LEVEL) {
                EntityFactory.newRandomWeapon().addToWorld(level)
            }
        }
    }

    private fun addArmor() = also {
        repeat(world.actualSize().zLength) { level ->
            repeat(ARMOR_PER_LEVEL) {
                EntityFactory.newRandomArmor().addToWorld(level)
            }
        }
    }
     */

    private fun addZombies() = also {
        repeat(world.actualSize().zLength) { level ->
            repeat(ZOMBIES_PER_LEVEL) {
                EntityFactory.newZombie().addToWorld(level)
            }
        }
    }

    private fun addExit() = also {
        EntityFactory.newExit().addToWorld(0)
    }

    companion object {

        fun defaultGame() = GameBuilder(
                worldSize =  GameConfig.WORLD_SIZE).buildGame()
    }
}
