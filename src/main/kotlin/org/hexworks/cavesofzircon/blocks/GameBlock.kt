package org.hexworks.cavesofzircon.blocks

import org.hexworks.cavesofzircon.builders.GameTileRepository
import org.hexworks.zircon.api.data.BlockSide
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.data.base.BlockBase

class GameBlock(private var defaultTile: Tile = GameTileRepository.floor())
    : BlockBase<Tile>() {

    override val layers
        get() = mutableListOf(defaultTile)


    override fun fetchSide(side: BlockSide): Tile {
        return GameTileRepository.EMPTY
    }
}
