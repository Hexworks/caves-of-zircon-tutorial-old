package org.hexworks.cavesofzircon.extensions

import org.hexworks.amethyst.api.Attribute
import org.hexworks.cavesofzircon.attributes.EntityPosition
import org.hexworks.cavesofzircon.attributes.EntityTile
import org.hexworks.cobalt.datatypes.extensions.map
import org.hexworks.cobalt.datatypes.extensions.orElseThrow
import org.hexworks.zircon.api.data.Tile
import kotlin.reflect.KClass

var AnyGameEntity.position
    get() = tryToFindAttribute(EntityPosition::class).position
    set(value) {
        findAttribute(EntityPosition::class).map {
            it.position = value
        }
    }

val AnyGameEntity.tile: Tile
    get() = this.tryToFindAttribute(EntityTile::class).tile

fun <T : Attribute> AnyGameEntity.tryToFindAttribute(klass: KClass<T>): T = findAttribute(klass).orElseThrow {
    NoSuchElementException("Entity '$this' has no property with type '${klass.simpleName}'.")
}
