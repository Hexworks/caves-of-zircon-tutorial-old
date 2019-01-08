package org.hexworks.cavesofzircon.builders

import org.hexworks.cavesofzircon.blocks.GameBlock

object GameBlockFactory {

    fun floor() = GameBlock(GameTileRepository.FLOOR)

    fun wall() = GameBlock(GameTileRepository.WALL)

}
