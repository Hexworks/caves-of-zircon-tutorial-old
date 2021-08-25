package norn.builders

import norn.attributes.*
import norn.attributes.flags.BlockOccupier
import norn.attributes.flags.VisionBlocker
import norn.attributes.types.*
import norn.commands.Attack
import norn.commands.Dig
import norn.entities.FogOfWar
import norn.extensions.GameEntity
import norn.systems.*
import norn.systems.Interactor
import norn.systems.npc.FungusGrowth
import norn.systems.npc.HunterSeeker
import norn.systems.npc.Wanderer
import norn.world.Game
import norn.world.GameContext
import org.hexworks.amethyst.api.Entities
import org.hexworks.amethyst.api.builder.EntityBuilder
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.api.GraphicalTilesetResources
import org.hexworks.zircon.api.Tiles
import kotlin.random.Random

fun <T : EntityType> newGameEntityOfType(type: T, init: EntityBuilder<T, GameContext>.() -> Unit) =
    Entities.newEntityOfType(type, init)

object EntityFactory {

    fun newPlayer() = newGameEntityOfType(Player) {
        attributes(
            Vision(9),
            EntityPosition(),
            BlockOccupier,
            CombatStats.create(
                maxHp = 100,
                attackValue = 10,
                defenseValue = 5
            ),
            EntityTile(GameTileRepository.PLAYER),
            EntityActions(Dig::class, Attack::class),
            Inventory(10),
            SpellBook(10),
            EnergyLevel(1000, 1000),
            Experience(),
            ZirconCounter(),
            Equipment(initialWeapon = newClub(), initialArmor = newJacket())
        )
        behaviors(InputReceiver, EnergyExpender)
        facets(
            Movable, CameraMover, StairClimber, StairDescender, Attackable, Spellcaster, Destructible, ZirconGatherer,
            ItemPicker, InventoryInspector, ItemDropper, EnergyExpender, DigestiveSystem, ExperienceAccumulator, Waiting, Interactor
        )
    }

    fun newRunestone() = newGameEntityOfType(Runestone) {
        attributes(
            VisionBlocker,
            EntityPosition(),
            BlockOccupier,
            EntityTile(GameTileRepository.RUNESTONE)
        )
        behaviors()
        facets(Interactor)
    }

    fun newPlaceholder() = newGameEntityOfType(Placeholder) {
        attributes()
        behaviors()
        facets()
    }

    fun newWall() = newGameEntityOfType(Wall) {
        attributes(
            VisionBlocker,
            EntityPosition(),
            BlockOccupier,
            EntityTile(GameTileRepository.WALL)
        )
        facets(Diggable)
    }

    fun newFungus(fungusSpread: FungusSpread = FungusSpread()) = newGameEntityOfType(Fungus) {
        attributes(
            BlockOccupier,
            EntityPosition(),
            EntityTile(GameTileRepository.FUNGUS),
            fungusSpread,
            CombatStats.create(
                maxHp = 10,
                attackValue = 0,
                defenseValue = 0
            )
        )
        facets(Attackable, Destructible)
        behaviors(FungusGrowth)
    }

    fun newStairsDown() = newGameEntityOfType(StairsDown) {
        attributes(
            EntityTile(GameTileRepository.STAIRS_DOWN),
            EntityPosition()
        )
    }

    fun newStairsUp() = newGameEntityOfType(StairsUp) {
        attributes(
            EntityTile(GameTileRepository.STAIRS_UP),
            EntityPosition()
        )
    }

    fun newExit() = newGameEntityOfType(Exit) {
        attributes(
            EntityTile(GameTileRepository.EXIT),
            EntityPosition()
        )
    }

    fun newFogOfWar(game: Game) = FogOfWar(game)

    fun newBat() = newGameEntityOfType(Bat) {
        attributes(BlockOccupier,
            EntityPosition(),
            EntityTile(GameTileRepository.BAT),
            CombatStats.create(
                maxHp = 5,
                attackValue = 2,
                defenseValue = 1
            ),
            EntityActions(Attack::class),
            Inventory(1).apply {
                addItem(newBatMeat())
            })
        facets(Movable, Attackable, ItemDropper, LootDropper, Destructible)
        behaviors(Wanderer)
    }

    fun newZombie() = newGameEntityOfType(Zombie) {
        attributes(
            BlockOccupier,
            EntityPosition(),
            EntityTile(GameTileRepository.ZOMBIE),
            Vision(10),
            CombatStats.create(
                maxHp = 25,
                attackValue = 8,
                defenseValue = 4
            ),
            Inventory(2).apply {
                addItem(newRandomWeapon())
                addItem(newRandomArmor())
            },
            EntityActions(Attack::class)
        )
        facets(Movable, Attackable, ItemDropper, LootDropper, Destructible)
        behaviors(HunterSeeker or Wanderer)
    }

    fun newZircon() = newGameEntityOfType(Zircon) {
        attributes(
            ItemIcon(
                Tiles.newBuilder()
                    .withName("white gem")
                    .withTileset(GraphicalTilesetResources.nethack16x16())
                    .buildGraphicTile()
            ),
            EntityPosition(),
            EntityTile(GameTileRepository.ZIRCON)
        )
    }


    fun newBatMeat() = newGameEntityOfType(BatMeat) {
        attributes(
            ItemIcon(
                Tiles.newBuilder()
                    .withName("Meatball")
                    .withTileset(GraphicalTilesetResources.nethack16x16())
                    .buildGraphicTile()
            ),
            NutritionalValue(750),
            EntityPosition(),
            EntityTile(GameTileRepository.BAT_MEAT)
        )
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

    fun newDagger() = newGameEntityOfType(Dagger) {
        attributes(
            ItemIcon(
                Tiles.newBuilder()
                    .withName("Dagger")
                    .withTileset(GraphicalTilesetResources.nethack16x16())
                    .buildGraphicTile()
            ),
            EntityPosition(),
            ItemCombatStats(
                attackValue = 4,
                combatItemType = "Weapon"
            ),
            EntityTile(GameTileRepository.DAGGER)
        )
    }

    fun newSword() = newGameEntityOfType(Sword) {
        attributes(
            ItemIcon(
                Tiles.newBuilder()
                    .withName("Short sword")
                    .withTileset(GraphicalTilesetResources.nethack16x16())
                    .buildGraphicTile()
            ),
            EntityPosition(),
            ItemCombatStats(
                attackValue = 6,
                combatItemType = "Weapon"
            ),
            EntityTile(GameTileRepository.SWORD)
        )
    }

    fun newStaff() = newGameEntityOfType(Staff) {
        attributes(
            ItemIcon(
                Tiles.newBuilder()
                    .withName("staff")
                    .withTileset(GraphicalTilesetResources.nethack16x16())
                    .buildGraphicTile()
            ),
            EntityPosition(),
            ItemCombatStats(
                attackValue = 4,
                defenseValue = 2,
                combatItemType = "Weapon"
            ),
            EntityTile(GameTileRepository.STAFF)
        )
    }

    fun newLightArmor() = newGameEntityOfType(LightArmor) {
        attributes(
            ItemIcon(
                Tiles.newBuilder()
                    .withName("Leather armor")
                    .withTileset(GraphicalTilesetResources.nethack16x16())
                    .buildGraphicTile()
            ),
            EntityPosition(),
            ItemCombatStats(
                defenseValue = 2,
                combatItemType = "Armor"
            ),
            EntityTile(GameTileRepository.LIGHT_ARMOR)
        )
    }

    fun newMediumArmor() = newGameEntityOfType(MediumArmor) {
        attributes(
            ItemIcon(
                Tiles.newBuilder()
                    .withName("Chain mail")
                    .withTileset(GraphicalTilesetResources.nethack16x16())
                    .buildGraphicTile()
            ),
            EntityPosition(),
            ItemCombatStats(
                defenseValue = 3,
                combatItemType = "Armor"
            ),
            EntityTile(GameTileRepository.MEDIUM_ARMOR)
        )
    }

    fun newHeavyArmor() = newGameEntityOfType(HeavyArmor) {
        attributes(
            ItemIcon(
                Tiles.newBuilder()
                    .withName("Plate mail")
                    .withTileset(GraphicalTilesetResources.nethack16x16())
                    .buildGraphicTile()
            ),
            EntityPosition(),
            ItemCombatStats(
                defenseValue = 4,
                combatItemType = "Armor"
            ),
            EntityTile(GameTileRepository.HEAVY_ARMOR)
        )
    }

    fun newClub() = newGameEntityOfType(Club) {
        attributes(
            ItemCombatStats(combatItemType = "Weapon"),
            EntityTile(GameTileRepository.CLUB),
            EntityPosition(),
            ItemIcon(
                Tiles.newBuilder()
                    .withName("Club")
                    .withTileset(GraphicalTilesetResources.nethack16x16())
                    .buildGraphicTile()
            )
        )
    }

    fun newJacket() = newGameEntityOfType(Jacket) {
        attributes(
            ItemCombatStats(combatItemType = "Armor"),
            EntityTile(GameTileRepository.JACKET),
            EntityPosition(),
            ItemIcon(
                Tiles.newBuilder()
                    .withName("Leather jacket")
                    .withTileset(GraphicalTilesetResources.nethack16x16())
                    .buildGraphicTile()
            )
        )
    }
}
