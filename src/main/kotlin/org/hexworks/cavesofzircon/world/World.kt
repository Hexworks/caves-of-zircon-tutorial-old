package org.hexworks.cavesofzircon.world

import org.hexworks.amethyst.api.Engine
import org.hexworks.amethyst.api.Engines
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cavesofzircon.attributes.Vision
import org.hexworks.cavesofzircon.attributes.types.Item
import org.hexworks.cavesofzircon.attributes.types.Player
import org.hexworks.cavesofzircon.blocks.GameBlock
import org.hexworks.cavesofzircon.builders.GameBlockFactory
import org.hexworks.cavesofzircon.extensions.GameEntity
import org.hexworks.cavesofzircon.extensions.blocksVision
import org.hexworks.cavesofzircon.extensions.filterType
import org.hexworks.cavesofzircon.extensions.position
import org.hexworks.cobalt.datatypes.Maybe
import org.hexworks.cobalt.datatypes.extensions.flatMap
import org.hexworks.cobalt.datatypes.extensions.fold
import org.hexworks.cobalt.datatypes.extensions.map
import org.hexworks.zircon.api.Positions
import org.hexworks.zircon.api.builder.game.GameAreaBuilder
import org.hexworks.zircon.api.data.Position
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.data.impl.Position3D
import org.hexworks.zircon.api.data.impl.Size3D
import org.hexworks.zircon.api.game.GameArea
import org.hexworks.zircon.api.screen.Screen
import org.hexworks.zircon.api.shape.EllipseFactory
import org.hexworks.zircon.api.shape.LineFactory
import org.hexworks.zircon.api.uievent.UIEvent
import kotlin.math.abs

class World(startingBlocks: Map<Position3D, GameBlock>,
            visibleSize: Size3D,
            actualSize: Size3D) : GameArea<Tile, GameBlock> by GameAreaBuilder.newBuilder<Tile, GameBlock>()
        .withVisibleSize(visibleSize)
        .withActualSize(actualSize)
        .withDefaultBlock(DEFAULT_BLOCK)
        .withLayersPerBlock(1)
        .build() {

    private val engine: Engine<GameContext> = Engines.newEngine()

    init {
        startingBlocks.forEach { pos, block ->
            setBlockAt(pos, block)
            block.entities.forEach { entity ->
                engine.addEntity(entity)
                entity.position = pos
            }
        }
    }

    fun update(
        screen: Screen,
        uiEvent: UIEvent,
        game: Game,
        player: Entity<Player, GameContext>
    ) {
        engine.update(GameContext(
                world = this,
                screen = screen,
                uiEvent = uiEvent,
                player = player
        ))
    }

    fun addEntity(entity: Entity<EntityType, GameContext>, position: Position3D) {
        entity.position = position
        engine.addEntity(entity)
        fetchBlockAt(position).map {
            it.addEntity(entity)
        }
    }

    fun addWorldEntity(entity: Entity<EntityType, GameContext>) {
        engine.addEntity(entity)
    }

    fun moveEntity(entity: GameEntity<EntityType>, position: Position3D): Boolean {
        var success = false
        val oldBlock = fetchBlockAt(entity.position)
        val newBlock = fetchBlockAt(position)

        if (bothBlocksPresent(oldBlock, newBlock)) {
            success = true
            oldBlock.get().removeEntity(entity)
            entity.position = position
            newBlock.get().addEntity(entity)
        }
        return success
    }

    fun findTopItem(position: Position3D) =
            fetchBlockAt(position).flatMap { block ->
                Maybe.ofNullable(block.entities.filterType<Item>().firstOrNull())
            }

    fun removeEntity(entity: Entity<EntityType, GameContext>) {
        fetchBlockAt(entity.position).map {
            it.removeEntity(entity)
        }
        engine.removeEntity(entity)
        entity.position = Position3D.unknown()
    }

    private fun bothBlocksPresent(oldBlock: Maybe<GameBlock>, newBlock: Maybe<GameBlock>) =
            oldBlock.isPresent && newBlock.isPresent

    fun addAtEmptyPosition(entity: GameEntity<EntityType>,
                           offset: Position3D = Positions.default3DPosition(),
                           size: Size3D = actualSize()): Boolean {
        return findEmptyLocationWithin(offset, size).fold(
                whenEmpty = {
                    false
                },
                whenPresent = { location ->
                    addEntity(entity, location)
                    true
                })

    }

    fun findEmptyLocationWithin(offset: Position3D, size: Size3D): Maybe<Position3D> {
        var position = Maybe.empty<Position3D>()
        val maxTries = 10
        var currentTry = 0
        while (position.isPresent.not() && currentTry < maxTries) {
            val pos = Positions.create3DPosition(
                    x = (Math.random() * size.xLength).toInt() + offset.x,
                    y = (Math.random() * size.yLength).toInt() + offset.y,
                    z = (Math.random() * size.zLength).toInt() + offset.z)
            fetchBlockAt(pos).map {
                if (it.isEmptyFloor) {
                    position = Maybe.of(pos)
                }
            }
            currentTry++
        }
        return position
    }

    fun isVisionBlockedAt(pos: Position3D): Boolean {
        return fetchBlockAt(pos).fold(whenEmpty = { false }, whenPresent = {
            it.entities.any(GameEntity<EntityType>::blocksVision)
        })
    }

    fun findVisiblePositionsFor(entity: GameEntity<EntityType>): Iterable<Position> {
        val centerPos = entity.position.to2DPosition()
        return entity.findAttribute(Vision::class).map { (radius) ->
            EllipseFactory.buildEllipse(
                    fromPosition = centerPos,
                    toPosition = centerPos.withRelativeX(radius).withRelativeY(radius))
                    .positions()
                    .flatMap { ringPos ->
                        val result = mutableListOf<Position>()
                        val iter = LineFactory.buildLine(centerPos, ringPos).iterator()
                        do {
                            val next = iter.next()
                            result.add(next)
                        } while (iter.hasNext() &&
                                isVisionBlockedAt(Position3D.from2DPosition(next, entity.position.z)).not())
                        result
                    }
        }.orElse(listOf())
    }

    fun whenCanSee(looker: GameEntity<EntityType>, target: GameEntity<EntityType>, fn: (path: List<Position>) -> Unit) {
        looker.findAttribute(Vision::class).map { (radius) ->
            val level = looker.position.z
            if (looker.position.isWithinRangeOf(target.position, radius)) {
                val path = LineFactory.buildLine(looker.position.to2DPosition(), target.position.to2DPosition())
                if (path.none { isVisionBlockedAt(Positions.from2DTo3D(it, level)) }) {
                    fn(path.positions().toList().drop(1))
                }
            }
        }
    }

    private fun Position3D.isWithinRangeOf(other: Position3D, radius: Int): Boolean {
        return this.isUnknown().not()
                && other.isUnknown().not()
                && this.z == other.z
                && abs(x - other.x) + abs(y - other.y) <= radius
    }

    companion object {
        private val DEFAULT_BLOCK = GameBlockFactory.floor()
    }
}
