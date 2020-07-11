package norn.attributes

import norn.attributes.types.Spell
import org.hexworks.amethyst.api.Attribute
import org.hexworks.cobalt.datatypes.Maybe

open class SpellBook(val size: Int) : Attribute {

    private val currentItems = mutableListOf<Spell>()

    val items: List<Spell>
        get() = currentItems.toList()

    val isEmpty: Boolean
        get() = currentItems.isEmpty()

    val isFull: Boolean
        get() = currentItems.size >= size

    fun findItemBy(name: String): Maybe<Spell> {
        return Maybe.ofNullable(items.firstOrNull { it.name == name })
    }

    fun addItem(spell: Spell): Boolean {
        return if (isFull.not()) {
            currentItems.add(spell)
        } else false
    }

    fun removeItem(spell: Spell): Boolean {
        return currentItems.remove(spell)
    }
}
