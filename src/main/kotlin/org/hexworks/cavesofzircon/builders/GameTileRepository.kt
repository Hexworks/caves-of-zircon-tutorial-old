package org.hexworks.cavesofzircon.builders

import org.hexworks.zircon.api.Tiles
import org.hexworks.zircon.api.data.CharacterTile
import org.hexworks.zircon.api.graphics.Symbols

object GameTileRepository {

    val EMPTY: CharacterTile = Tiles.empty()

    val FLOOR: CharacterTile = Tiles.newBuilder()
            .withCharacter(Symbols.INTERPUNCT)
            .withForegroundColor(GameColors.FLOOR_FOREGROUND)
            .withBackgroundColor(GameColors.FLOOR_BACKGROUND)
            .buildCharacterTile()

    val WALL: CharacterTile = Tiles.newBuilder()
            .withCharacter('#')
            .withForegroundColor(GameColors.WALL_FOREGROUND)
            .withBackgroundColor(GameColors.WALL_BACKGROUND)
            .buildCharacterTile()

    val STAIRS_UP = Tiles.newBuilder()
            .withCharacter('<')
            .withForegroundColor(GameColors.ACCENT_COLOR)
            .withBackgroundColor(GameColors.FLOOR_BACKGROUND)
            .buildCharacterTile()

    val STAIRS_DOWN = Tiles.newBuilder()
            .withCharacter('>')
            .withForegroundColor(GameColors.ACCENT_COLOR)
            .withBackgroundColor(GameColors.FLOOR_BACKGROUND)
            .buildCharacterTile()

    val PLAYER = Tiles.newBuilder()
            .withCharacter('@')
            .withBackgroundColor(GameColors.FLOOR_BACKGROUND)
            .withForegroundColor(GameColors.ACCENT_COLOR)
            .buildCharacterTile()

    val FUNGUS = Tiles.newBuilder()
            .withCharacter('f')
            .withBackgroundColor(GameColors.FLOOR_BACKGROUND)
            .withForegroundColor(GameColors.FUNGUS_COLOR)
            .buildCharacterTile()

    val BAT = Tiles.newBuilder()
            .withCharacter('b')
            .withBackgroundColor(GameColors.FLOOR_BACKGROUND)
            .withForegroundColor(GameColors.BAT_COLOR)
            .buildCharacterTile()

    val BAT_MEAT = Tiles.newBuilder()
            .withCharacter('m')
            .withBackgroundColor(GameColors.FLOOR_BACKGROUND)
            .withForegroundColor(GameColors.BAT_MEAT_COLOR)
            .buildCharacterTile()

    val ZIRCON  = Tiles.newBuilder()
            .withCharacter(',')
            .withBackgroundColor(GameColors.FLOOR_BACKGROUND)
            .withForegroundColor(GameColors.ZIRCON_COLOR)
            .buildCharacterTile()

    val UNREVEALED = Tiles.newBuilder()
            .withCharacter(' ')
            .withBackgroundColor(GameColors.UNREVEALED_COLOR)
            .buildCharacterTile()
}
