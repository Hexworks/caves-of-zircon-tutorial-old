package org.hexworks.cavesofzircon

import org.hexworks.zircon.api.AppConfigs
import org.hexworks.zircon.api.CP437TilesetResources
import org.hexworks.zircon.api.ColorThemes
import org.hexworks.zircon.api.Sizes

object GameConfig {

    // game
    const val DUNGEON_LEVELS = 2

    // look & feel
    val TILESET = CP437TilesetResources.rogueYun16x16()
    val THEME = ColorThemes.zenburnVanilla()
    const val SIDEBAR_WIDTH = 18
    const val LOG_AREA_HEIGHT = 8

    // sizing
    const val WINDOW_WIDTH = 80
    const val WINDOW_HEIGHT = 50

    val WORLD_SIZE = Sizes.create3DSize(WINDOW_WIDTH * 2, WINDOW_HEIGHT * 2 , DUNGEON_LEVELS)

    // entities
    const val FUNGI_PER_LEVEL = 15
    const val BATS_PER_LEVEL = 10
    const val ZIRCONS_PER_LEVEL = 20
    const val MAXIMUM_FUNGUS_SPREAD = 20
    const val ZOMBIES_PER_LEVEL = 3

    fun buildAppConfig() = AppConfigs.newConfig()
            .enableBetaFeatures()
            .withDefaultTileset(TILESET)
            .withSize(Sizes.create(WINDOW_WIDTH, WINDOW_HEIGHT))
            .build()

}
