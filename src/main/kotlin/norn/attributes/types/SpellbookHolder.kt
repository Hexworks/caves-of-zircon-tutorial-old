package norn.attributes.types

import norn.attributes.SpellBook
import norn.extensions.GameSpellbookHolder
import org.hexworks.amethyst.api.entity.EntityType

interface SpellbookHolder : EntityType

fun GameSpellbookHolder.addItem(spell: Spell) = spellBook.addItem(spell)

fun GameSpellbookHolder.removeItem(spell: Spell) = spellBook.removeItem(spell)

val GameSpellbookHolder.spellBook: SpellBook
    get() = findAttribute(SpellBook::class).get()
