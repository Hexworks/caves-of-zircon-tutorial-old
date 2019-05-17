package org.hexworks.cavesofzircon.attributes

import org.hexworks.amethyst.api.Attribute
import org.hexworks.cavesofzircon.extensions.GameItem
import org.hexworks.cobalt.datatypes.Identifier
import org.hexworks.cobalt.datatypes.Maybe

class Inventory(val size: Int) : Attribute {

    private val currentItems = mutableListOf<GameItem>()

    val items: List<GameItem>
        get() = currentItems.toList()

    val isEmpty: Boolean
        get() = currentItems.isEmpty()

    val isFull: Boolean
        get() = currentItems.size >= size

    fun findItemBy(id: Identifier): Maybe<GameItem> {
        return Maybe.ofNullable(items.firstOrNull { it.id == id })
    }

    fun addItem(item: GameItem): Boolean {
        return if (isFull.not()) {
            currentItems.add(item)
        } else false
    }

    fun removeItem(entity: GameItem): Boolean {
        return currentItems.remove(entity)
    }
}
