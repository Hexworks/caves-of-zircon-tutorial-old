package org.hexworks.cavesofzircon.builders

import org.hexworks.amethyst.api.Entities
import org.hexworks.amethyst.api.builder.EntityBuilder
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cavesofzircon.attributes.CombatStats
import org.hexworks.cavesofzircon.attributes.EntityActions
import org.hexworks.cavesofzircon.attributes.EntityPosition
import org.hexworks.cavesofzircon.attributes.EntityTile
import org.hexworks.cavesofzircon.attributes.FungusSpread
import org.hexworks.cavesofzircon.attributes.Vision
import org.hexworks.cavesofzircon.attributes.flags.BlockOccupier
import org.hexworks.cavesofzircon.attributes.flags.VisionBlocker
import org.hexworks.cavesofzircon.attributes.types.Fungus
import org.hexworks.cavesofzircon.attributes.types.Player
import org.hexworks.cavesofzircon.attributes.types.StairsDown
import org.hexworks.cavesofzircon.attributes.types.StairsUp
import org.hexworks.cavesofzircon.attributes.types.Wall
import org.hexworks.cavesofzircon.commands.Attack
import org.hexworks.cavesofzircon.commands.Dig
import org.hexworks.cavesofzircon.entities.FogOfWar
import org.hexworks.cavesofzircon.systems.Attackable
import org.hexworks.cavesofzircon.systems.CameraMover
import org.hexworks.cavesofzircon.systems.Destructible
import org.hexworks.cavesofzircon.systems.Diggable
import org.hexworks.cavesofzircon.systems.FungusGrowth
import org.hexworks.cavesofzircon.systems.InputReceiver
import org.hexworks.cavesofzircon.systems.Movable
import org.hexworks.cavesofzircon.systems.StairClimber
import org.hexworks.cavesofzircon.systems.StairDescender
import org.hexworks.cavesofzircon.world.Game
import org.hexworks.cavesofzircon.world.GameContext

fun <T : EntityType> newGameEntityOfType(type: T, init: EntityBuilder<T, GameContext>.() -> Unit) =
        Entities.newEntityOfType(type, init)

object EntityFactory {

    fun newFogOfWar(game: Game) = FogOfWar(game)

    fun newWall() = newGameEntityOfType(Wall) {
        attributes(
                VisionBlocker,
                EntityPosition(),
                BlockOccupier,
                EntityTile(GameTileRepository.WALL))
        facets(Diggable)
    }

    fun newStairsDown() = newGameEntityOfType(StairsDown) {
        attributes(EntityTile(GameTileRepository.STAIRS_DOWN),
                EntityPosition())
    }

    fun newStairsUp() = newGameEntityOfType(StairsUp) {
        attributes(EntityTile(GameTileRepository.STAIRS_UP),
                EntityPosition())
    }

    fun newPlayer() = newGameEntityOfType(Player) {
        attributes(
                Vision(9),
                EntityPosition(),
                CombatStats.create(
                        maxHp = 100,
                        attackValue = 10,
                        defenseValue = 5),
                EntityTile(GameTileRepository.PLAYER),
                EntityActions(Dig::class, Attack::class))
        behaviors(InputReceiver)
        facets(Movable, CameraMover, StairClimber, StairDescender)
    }

    fun newFungus(fungusSpread: FungusSpread = FungusSpread()) = newGameEntityOfType(Fungus) {
        attributes(BlockOccupier,
                EntityPosition(),
                CombatStats.create(
                        maxHp = 10,
                        attackValue = 0,
                        defenseValue = 0),
                EntityTile(GameTileRepository.FUNGUS),
                fungusSpread)
        facets(Attackable, Destructible)
        behaviors(FungusGrowth)
    }
}

