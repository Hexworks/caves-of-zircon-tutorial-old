package org.hexworks.cavesofzircon.attributes

import org.hexworks.amethyst.api.Attribute
import org.hexworks.cavesofzircon.GameConfig

data class FungusSpread(
        var spreadCount: Int = 0,
        val maximumSpread: Int = GameConfig.MAXIMUM_FUNGUS_SPREAD) : Attribute
