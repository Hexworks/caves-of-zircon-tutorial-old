package norn.attributes.types

import norn.attributes.SpellBook
import org.hexworks.amethyst.api.entity.EntityType
import norn.extensions.GameSpellbookHolder

interface SpellbookHolder : EntityType

fun GameSpellbookHolder.addItem(spell: Spell) = spellBook.addItem(spell)

fun GameSpellbookHolder.removeItem(spell: Spell) = spellBook.removeItem(spell)

val GameSpellbookHolder.spellBook: SpellBook
    get() = findAttribute(SpellBook::class).get()
