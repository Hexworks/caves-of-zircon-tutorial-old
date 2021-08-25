package norn.attributes.types

import org.hexworks.amethyst.api.base.BaseEntityType

abstract class ContainerEntity<T>(override val name: String = "unknown",
                                  override val description: String = "", contained: T) : BaseEntityType() {
    val contained : T
        get() {
            return contained
        }
}