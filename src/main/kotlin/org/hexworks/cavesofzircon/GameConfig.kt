package org.hexworks.cavesofzircon

import org.hexworks.zircon.api.AppConfigs
import org.hexworks.zircon.api.CP437TilesetResources
import org.hexworks.zircon.api.ColorThemes
import org.hexworks.zircon.api.Sizes

object GameConfig {

    // look & feel
    val TILESET = CP437TilesetResources.rogueYun16x16()
    val THEME = ColorThemes.zenburnVanilla()
    const val SIDEBAR_WIDTH = 18
    const val LOG_AREA_HEIGHT = 8

    // sizing
    const val WINDOW_WIDTH = 80
    const val WINDOW_HEIGHT = 50

    fun buildAppConfig() = AppConfigs.newConfig()
            .enableBetaFeatures()
            .withDefaultTileset(TILESET)
            .withSize(Sizes.create(WINDOW_WIDTH, WINDOW_HEIGHT))
            .build()

}
