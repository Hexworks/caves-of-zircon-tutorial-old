package org.hexworks.cavesofzircon.builders

import org.hexworks.amethyst.api.Entities
import org.hexworks.amethyst.api.builder.EntityBuilder
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cavesofzircon.attributes.CombatStats
import org.hexworks.cavesofzircon.attributes.EnergyLevel
import org.hexworks.cavesofzircon.attributes.EntityActions
import org.hexworks.cavesofzircon.attributes.EntityPosition
import org.hexworks.cavesofzircon.attributes.EntityTile
import org.hexworks.cavesofzircon.attributes.Equipment
import org.hexworks.cavesofzircon.attributes.Experience
import org.hexworks.cavesofzircon.attributes.FungusSpread
import org.hexworks.cavesofzircon.attributes.Inventory
import org.hexworks.cavesofzircon.attributes.ItemCombatStats
import org.hexworks.cavesofzircon.attributes.ItemIcon
import org.hexworks.cavesofzircon.attributes.NutritionalValue
import org.hexworks.cavesofzircon.attributes.Vision
import org.hexworks.cavesofzircon.attributes.ZirconCounter
import org.hexworks.cavesofzircon.attributes.flags.BlockOccupier
import org.hexworks.cavesofzircon.attributes.flags.VisionBlocker
import org.hexworks.cavesofzircon.attributes.types.Armor
import org.hexworks.cavesofzircon.attributes.types.Bat
import org.hexworks.cavesofzircon.attributes.types.BatMeat
import org.hexworks.cavesofzircon.attributes.types.Club
import org.hexworks.cavesofzircon.attributes.types.Dagger
import org.hexworks.cavesofzircon.attributes.types.Exit
import org.hexworks.cavesofzircon.attributes.types.Fungus
import org.hexworks.cavesofzircon.attributes.types.HeavyArmor
import org.hexworks.cavesofzircon.attributes.types.Jacket
import org.hexworks.cavesofzircon.attributes.types.LightArmor
import org.hexworks.cavesofzircon.attributes.types.MediumArmor
import org.hexworks.cavesofzircon.attributes.types.Player
import org.hexworks.cavesofzircon.attributes.types.Staff
import org.hexworks.cavesofzircon.attributes.types.StairsDown
import org.hexworks.cavesofzircon.attributes.types.StairsUp
import org.hexworks.cavesofzircon.attributes.types.Sword
import org.hexworks.cavesofzircon.attributes.types.Wall
import org.hexworks.cavesofzircon.attributes.types.Weapon
import org.hexworks.cavesofzircon.attributes.types.Zircon
import org.hexworks.cavesofzircon.attributes.types.Zombie
import org.hexworks.cavesofzircon.commands.Attack
import org.hexworks.cavesofzircon.commands.Dig
import org.hexworks.cavesofzircon.entities.FogOfWar
import org.hexworks.cavesofzircon.entities.FogOfWarMultiPlayer
import org.hexworks.cavesofzircon.extensions.GameEntity
import org.hexworks.cavesofzircon.systems.Attackable
import org.hexworks.cavesofzircon.systems.CameraMover
import org.hexworks.cavesofzircon.systems.Destructible
import org.hexworks.cavesofzircon.systems.DigestiveSystem
import org.hexworks.cavesofzircon.systems.Diggable
import org.hexworks.cavesofzircon.systems.EnergyExpender
import org.hexworks.cavesofzircon.systems.ExperienceAccumulator
import org.hexworks.cavesofzircon.systems.FungusGrowth
import org.hexworks.cavesofzircon.systems.HunterSeeker
import org.hexworks.cavesofzircon.systems.InputReceiver
import org.hexworks.cavesofzircon.systems.InventoryInspector
import org.hexworks.cavesofzircon.systems.ItemDropper
import org.hexworks.cavesofzircon.systems.ItemPicker
import org.hexworks.cavesofzircon.systems.LootDropper
import org.hexworks.cavesofzircon.systems.Movable
import org.hexworks.cavesofzircon.systems.StairClimber
import org.hexworks.cavesofzircon.systems.StairDescender
import org.hexworks.cavesofzircon.systems.Wanderer
import org.hexworks.cavesofzircon.systems.ZirconGatherer
import org.hexworks.cavesofzircon.world.Game
import org.hexworks.cavesofzircon.world.GameContext
import org.hexworks.cavesofzircon.world.PlayerDTO
import org.hexworks.zircon.api.GraphicalTilesetResources
import org.hexworks.zircon.api.Tiles
import kotlin.random.Random

fun <T : EntityType> newGameEntityOfType(type: T, init: EntityBuilder<T, GameContext>.() -> Unit) =
        Entities.newEntityOfType(type, init)

object EntityFactory {

    fun newFogOfWar(game: Game) = FogOfWar(game)
    fun newFogOfWarMulti(
        game: Game,
        player: Entity<Player, GameContext>
    ) = FogOfWarMultiPlayer(game, player)

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

    fun newExit() = newGameEntityOfType(Exit) {
        attributes(EntityTile(GameTileRepository.EXIT),
                EntityPosition())
    }

    fun newPlayer(playerDTO: PlayerDTO) = newGameEntityOfType(Player) {
        attributes(
                Experience(playerDTO.currentXP, playerDTO.currentLevel),
                Vision(playerDTO.visionRadius),
                EntityPosition(),
                BlockOccupier,
                CombatStats.create(
                        maxHp = playerDTO.combatStats.maxHp,
                        attackValue = playerDTO.combatStats.attackValue,
                        defenseValue = playerDTO.combatStats.defenseValue),
                EntityTile(GameTileRepository.PLAYER),
                EntityActions(Dig::class, Attack::class),
                Inventory(playerDTO.inventorySize),
                EnergyLevel(playerDTO.energyLevel, playerDTO.energyLevel),
                Equipment(
                        initialWeapon = newClub(),
                        initialArmor = newJacket()),
                ZirconCounter()
        )
        behaviors(InputReceiver, EnergyExpender)
        facets(Movable, CameraMover, StairClimber, StairDescender, Attackable, ExperienceAccumulator, Destructible,
                ZirconGatherer, ItemPicker, InventoryInspector, ItemDropper, EnergyExpender, DigestiveSystem)
    }

    fun newPlayer() = newGameEntityOfType(Player) {
        attributes(
                Experience(),
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
                EnergyLevel(1000, 1000),
                Equipment(
                        initialWeapon = newClub(),
                        initialArmor = newJacket()),
                ZirconCounter())
        behaviors(InputReceiver, EnergyExpender)
        facets(Movable, CameraMover, StairClimber, StairDescender, Attackable, ExperienceAccumulator, Destructible,
                ZirconGatherer, ItemPicker, InventoryInspector, ItemDropper, EnergyExpender, DigestiveSystem)
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

    fun newZombie() = newGameEntityOfType(Zombie) {
        attributes(BlockOccupier,
                EntityPosition(),
                EntityTile(GameTileRepository.ZOMBIE),
                Vision(10),
                CombatStats.create(
                        maxHp = 25,
                        attackValue = 8,
                        defenseValue = 4),
                Inventory(2).apply {
                    addItem(newRandomWeapon())
                    addItem(newRandomArmor())
                },
                EntityActions(Attack::class))
        facets(Movable, Attackable, ItemDropper, LootDropper, Destructible)
        behaviors(HunterSeeker or Wanderer)
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

    fun newDagger() = newGameEntityOfType(Dagger) {
        attributes(ItemIcon(Tiles.newBuilder()
                .withName("Dagger")
                .withTileset(GraphicalTilesetResources.nethack16x16())
                .buildGraphicTile()),
                EntityPosition(),
                ItemCombatStats(
                        attackValue = 4,
                        combatItemType = "Weapon"),
                EntityTile(GameTileRepository.DAGGER))
    }

    fun newSword() = newGameEntityOfType(Sword) {
        attributes(ItemIcon(Tiles.newBuilder()
                .withName("Short sword")
                .withTileset(GraphicalTilesetResources.nethack16x16())
                .buildGraphicTile()),
                EntityPosition(),
                ItemCombatStats(
                        attackValue = 6,
                        combatItemType = "Weapon"),
                EntityTile(GameTileRepository.SWORD))
    }

    fun newStaff() = newGameEntityOfType(Staff) {
        attributes(ItemIcon(Tiles.newBuilder()
                .withName("staff")
                .withTileset(GraphicalTilesetResources.nethack16x16())
                .buildGraphicTile()),
                EntityPosition(),
                ItemCombatStats(
                        attackValue = 4,
                        defenseValue = 2,
                        combatItemType = "Weapon"),
                EntityTile(GameTileRepository.STAFF))
    }

    fun newLightArmor() = newGameEntityOfType(LightArmor) {
        attributes(ItemIcon(Tiles.newBuilder()
                .withName("Leather armor")
                .withTileset(GraphicalTilesetResources.nethack16x16())
                .buildGraphicTile()),
                EntityPosition(),
                ItemCombatStats(
                        defenseValue = 2,
                        combatItemType = "Armor"),
                EntityTile(GameTileRepository.LIGHT_ARMOR))
    }

    fun newMediumArmor() = newGameEntityOfType(MediumArmor) {
        attributes(ItemIcon(Tiles.newBuilder()
                .withName("Chain mail")
                .withTileset(GraphicalTilesetResources.nethack16x16())
                .buildGraphicTile()),
                EntityPosition(),
                ItemCombatStats(
                        defenseValue = 3,
                        combatItemType = "Armor"),
                EntityTile(GameTileRepository.MEDIUM_ARMOR))
    }

    fun newHeavyArmor() = newGameEntityOfType(HeavyArmor) {
        attributes(ItemIcon(Tiles.newBuilder()
                .withName("Plate mail")
                .withTileset(GraphicalTilesetResources.nethack16x16())
                .buildGraphicTile()),
                EntityPosition(),
                ItemCombatStats(
                        defenseValue = 4,
                        combatItemType = "Armor"),
                EntityTile(GameTileRepository.HEAVY_ARMOR))
    }

    fun newClub() = newGameEntityOfType(Club) {
        attributes(ItemCombatStats(combatItemType = "Weapon"),
                EntityTile(GameTileRepository.CLUB),
                EntityPosition(),
                ItemIcon(Tiles.newBuilder()
                        .withName("Club")
                        .withTileset(GraphicalTilesetResources.nethack16x16())
                        .buildGraphicTile()))
    }

    fun newJacket() = newGameEntityOfType(Jacket) {
        attributes(ItemCombatStats(combatItemType = "Armor"),
                EntityTile(GameTileRepository.JACKET),
                EntityPosition(),
                ItemIcon(Tiles.newBuilder()
                        .withName("Leather jacket")
                        .withTileset(GraphicalTilesetResources.nethack16x16())
                        .buildGraphicTile()))
    }

    fun newRandomWeapon(): GameEntity<Weapon> = when (Random.nextInt(3)) {
        0 -> newDagger()
        1 -> newSword()
        else -> newStaff()
    }

    fun newRandomArmor(): GameEntity<Armor> = when (Random.nextInt(3)) {
        0 -> newLightArmor()
        1 -> newMediumArmor()
        else -> newHeavyArmor()
    }


}

