package norn.attributes.types

import org.hexworks.amethyst.api.entity.EntityType
import norn.attributes.ZirconCounter
import norn.extensions.GameEntity

interface ZirconHolder : EntityType

val GameEntity<ZirconHolder>.zirconCounter: ZirconCounter
    get() = findAttribute(ZirconCounter::class).get()
