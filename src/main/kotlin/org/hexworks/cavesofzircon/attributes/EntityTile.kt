package org.hexworks.cavesofzircon.attributes

import org.hexworks.amethyst.api.Attribute
import org.hexworks.zircon.api.Tiles
import org.hexworks.zircon.api.data.Tile

/**
 * Contains the tile of an entity.
 */
data class EntityTile(val tile: Tile = Tiles.empty()) : Attribute
