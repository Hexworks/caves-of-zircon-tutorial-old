package norn.attributes.types

import norn.attributes.ZirconCounter
import norn.extensions.GameEntity
import org.hexworks.amethyst.api.entity.EntityType

interface ZirconHolder : EntityType

val GameEntity<ZirconHolder>.zirconCounter: ZirconCounter
    get() = findAttribute(ZirconCounter::class).get()
