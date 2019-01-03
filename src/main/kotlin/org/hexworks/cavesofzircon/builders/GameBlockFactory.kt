package org.hexworks.cavesofzircon.builders

import org.hexworks.cavesofzircon.blocks.GameBlock
import org.hexworks.zircon.api.data.impl.Position3D

object GameBlockFactory {

    fun floor() = GameBlock(GameTileRepository.floor())

    fun wall() = GameBlock(GameTileRepository.wall())

}
