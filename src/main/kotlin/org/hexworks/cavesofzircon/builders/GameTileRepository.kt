package org.hexworks.cavesofzircon.builders

import org.hexworks.zircon.api.Tiles
import org.hexworks.zircon.api.data.CharacterTile
import org.hexworks.zircon.api.graphics.Symbols

object GameTileRepository {

    val EMPTY: CharacterTile = Tiles.empty()

    fun floor(): CharacterTile {
        return Tiles.newBuilder()
                .withCharacter(Symbols.INTERPUNCT)
                .withForegroundColor(GameColors.FLOOR_FOREGROUND)
                .withBackgroundColor(GameColors.FLOOR_BACKGROUND)
                .buildCharacterTile()
    }

    fun wall() = Tiles.newBuilder()
            .withCharacter('#')
            .withForegroundColor(GameColors.WALL_FOREGROUND)
            .withBackgroundColor(GameColors.WALL_BACKGROUND)
            .buildCharacterTile()

}
