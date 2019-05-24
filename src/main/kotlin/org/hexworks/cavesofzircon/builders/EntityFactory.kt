package org.hexworks.cavesofzircon.builders

import org.hexworks.amethyst.api.Entities
import org.hexworks.amethyst.api.builder.EntityBuilder
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cavesofzircon.attributes.CombatStats
import org.hexworks.cavesofzircon.attributes.EnergyLevel
import org.hexworks.cavesofzircon.attributes.EntityActions
import org.hexworks.cavesofzircon.attributes.EntityPosition
import org.hexworks.cavesofzircon.attributes.EntityTile
import org.hexworks.cavesofzircon.attributes.FungusSpread
import org.hexworks.cavesofzircon.attributes.Inventory
import org.hexworks.cavesofzircon.attributes.ItemIcon
import org.hexworks.cavesofzircon.attributes.NutritionalValue
import org.hexworks.cavesofzircon.attributes.Vision
import org.hexworks.cavesofzircon.attributes.flags.BlockOccupier
import org.hexworks.cavesofzircon.attributes.flags.VisionBlocker
import org.hexworks.cavesofzircon.attributes.types.Bat
import org.hexworks.cavesofzircon.attributes.types.BatMeat
import org.hexworks.cavesofzircon.attributes.types.Fungus
import org.hexworks.cavesofzircon.attributes.types.Player
import org.hexworks.cavesofzircon.attributes.types.StairsDown
import org.hexworks.cavesofzircon.attributes.types.StairsUp
import org.hexworks.cavesofzircon.attributes.types.Wall
import org.hexworks.cavesofzircon.attributes.types.Zircon
import org.hexworks.cavesofzircon.commands.Attack
import org.hexworks.cavesofzircon.commands.Dig
import org.hexworks.cavesofzircon.entities.FogOfWar
import org.hexworks.cavesofzircon.systems.Attackable
import org.hexworks.cavesofzircon.systems.CameraMover
import org.hexworks.cavesofzircon.systems.Destructible
import org.hexworks.cavesofzircon.systems.DigestiveSystem
import org.hexworks.cavesofzircon.systems.Diggable
import org.hexworks.cavesofzircon.systems.EnergyExpender
import org.hexworks.cavesofzircon.systems.FungusGrowth
import org.hexworks.cavesofzircon.systems.InputReceiver
import org.hexworks.cavesofzircon.systems.InventoryInspector
import org.hexworks.cavesofzircon.systems.ItemDropper
import org.hexworks.cavesofzircon.systems.ItemPicker
import org.hexworks.cavesofzircon.systems.LootDropper
import org.hexworks.cavesofzircon.systems.Movable
import org.hexworks.cavesofzircon.systems.StairClimber
import org.hexworks.cavesofzircon.systems.StairDescender
import org.hexworks.cavesofzircon.systems.Wanderer
import org.hexworks.cavesofzircon.world.Game
import org.hexworks.cavesofzircon.world.GameContext
import org.hexworks.zircon.api.GraphicalTilesetResources
import org.hexworks.zircon.api.Tiles

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
                BlockOccupier,
                CombatStats.create(
                        maxHp = 100,
                        attackValue = 10,
                        defenseValue = 5),
                EntityTile(GameTileRepository.PLAYER),
                EntityActions(Dig::class, Attack::class),
                Inventory(10),
                EnergyLevel(1000, 1000))
        behaviors(InputReceiver, EnergyExpender)
        facets(Movable, CameraMover, StairClimber, StairDescender, Attackable, Destructible,
                ItemPicker, InventoryInspector, ItemDropper, EnergyExpender, DigestiveSystem)
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

    fun newBat() = newGameEntityOfType(Bat) {
        attributes(BlockOccupier,
                EntityPosition(),
                EntityTile(GameTileRepository.BAT),
                CombatStats.create(
                        maxHp = 5,
                        attackValue = 2,
                        defenseValue = 1),
                EntityActions(Attack::class),
                Inventory(1).apply {
                    addItem(newBatMeat())
                })
        facets(Movable, Attackable, ItemDropper, LootDropper, Destructible)
        behaviors(Wanderer)
    }

    fun newBatMeat() = newGameEntityOfType(BatMeat) {
        attributes(ItemIcon(Tiles.newBuilder()
                .withName("Meatball")
                .withTileset(GraphicalTilesetResources.nethack16x16())
                .buildGraphicTile()),
                NutritionalValue(750),
                EntityPosition(),
                EntityTile(GameTileRepository.BAT_MEAT))
    }

    fun newZircon() = newGameEntityOfType(Zircon) {
        attributes(ItemIcon(Tiles.newBuilder()
                .withName("white gem")
                .withTileset(GraphicalTilesetResources.nethack16x16())
                .buildGraphicTile()),
                EntityPosition(),
                EntityTile(GameTileRepository.ZIRCON))
    }
}

