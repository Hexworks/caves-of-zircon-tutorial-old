package org.hexworks.cavesofzircon.view

import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cavesofzircon.GameConfig
import org.hexworks.cavesofzircon.attributes.types.Player
import org.hexworks.cavesofzircon.builders.EntityFactory
import org.hexworks.cavesofzircon.extensions.GameEntity
import org.hexworks.cavesofzircon.world.CombatStatsDTO
import org.hexworks.cavesofzircon.world.Game
import org.hexworks.cavesofzircon.world.GameBuilder
import org.hexworks.cavesofzircon.world.GameContext
import org.hexworks.cavesofzircon.world.PlayerDTO
import org.hexworks.cobalt.datatypes.Identifier
import org.hexworks.zircon.api.ColorThemes
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.data.Size
import org.hexworks.zircon.api.extensions.onComponentEvent
import org.hexworks.zircon.api.graphics.BoxType
import org.hexworks.zircon.api.mvc.base.BaseView
import org.hexworks.zircon.api.uievent.ComponentEventType
import org.hexworks.zircon.api.uievent.Processed
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

class StartView : BaseView() {

    override val theme = ColorThemes.arc()

    private val sockets: ConcurrentMap<Identifier, String> = ConcurrentHashMap()
    private val players: ConcurrentMap<Identifier, GameEntity<Player>> = ConcurrentHashMap()
    private val game: Game = GameBuilder.defaultGame()

    private fun createNewPlayer(): Pair<Identifier, Entity<Player, GameContext>> {
        val playerAttributes = PlayerDTO(
            currentXP = 90,
            currentLevel = 1,
            visionRadius = 10,
            combatStats = CombatStatsDTO(
                maxHp = 100,
                attackValue = 10,
                defenseValue = 5
            ),
            inventorySize = 10,
            energyLevel = 1000
        )

        val player: Entity<Player, GameContext> = EntityFactory
            .newPlayer(playerAttributes)
            .addToWorld(
                atLevel = GameConfig.DUNGEON_LEVELS - 1,
                atArea = game.world.visibleSize().to2DSize()
            )

        return player.id to player
    }

    // TODO: Duplicate from GameBuilder - refactor
    private fun <T : EntityType> GameEntity<T>.addToWorld(
        atLevel: Int,
        atArea: Size = game.world.actualSize().to2DSize()): GameEntity<T> {
        game.world.addAtEmptyPosition(this,
                                      offset = org.hexworks.zircon.api.Positions.default3DPosition().withZ(atLevel),
                                      size = org.hexworks.zircon.api.data.impl.Size3D.from2DSize(atArea))
        return this
    }

    private fun addPlayerToGame(): Entity<Player, GameContext> {
        val (playerId: Identifier, player: Entity<Player, GameContext>) = createNewPlayer()
        players[playerId] = player
        sockets[playerId] = ""

        game.world
            .addWorldEntity(EntityFactory.newFogOfWarMulti(game, player))

        return player
    }

    fun removePlayerFromGame(playerID: Identifier) {
        players.remove(playerID)
    }

    override fun onDock() {
        val msg = "Welcome to Caves of Zircon."
        val header = Components.textBox()
                .withContentWidth(msg.length)
                .addHeader(msg)
                .addNewLine()
                .withAlignmentWithin(screen, ComponentAlignment.CENTER)
                .build()
        val startButton = Components.button()
                .withAlignmentAround(header, ComponentAlignment.BOTTOM_CENTER)
                .withText("Start!")
                .wrapSides(false)
                .withBoxType(BoxType.SINGLE)
                .wrapWithShadow()
                .wrapWithBox()
                .build()

        // TODO: tutorial
        startButton.onComponentEvent(ComponentEventType.ACTIVATED) {
            val player: Entity<Player, GameContext> = addPlayerToGame()
            replaceWith(PlayView(player, game))
            close()
            Processed
        }

        screen.addComponent(header)
        screen.addComponent(startButton)
    }
}
