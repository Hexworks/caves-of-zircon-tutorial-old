package org.hexworks.cavesofzircon.world

import org.hexworks.cavesofzircon.blocks.GameBlock
import org.hexworks.cavesofzircon.builders.GameBlockFactory
import org.hexworks.cobalt.datatypes.extensions.map
import org.hexworks.zircon.api.builder.game.GameAreaBuilder
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.data.impl.Position3D
import org.hexworks.zircon.api.data.impl.Size3D
import org.hexworks.zircon.api.game.GameArea


class World(startingBlocks: Map<Position3D, GameBlock>,
            visibleSize: Size3D,
            actualSize: Size3D) : GameArea<Tile, GameBlock> by GameAreaBuilder.newBuilder<Tile, GameBlock>()
        .withVisibleSize(visibleSize)
        .withActualSize(actualSize)
        .withDefaultBlock(DEFAULT_BLOCK)
        .withLayersPerBlock(2)
        .build() {

    init {
        startingBlocks.forEach { pos, block ->
            setBlockAt(pos, block)
        }
    }

    fun withBlockAt(position: Position3D, fn: (GameBlock) -> Unit) {
        fetchBlockAt(position).map(fn)
    }

    companion object {
        private val DEFAULT_BLOCK = GameBlockFactory.floor()
    }
}
