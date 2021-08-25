package norn.attributes.types

import org.hexworks.amethyst.api.base.BaseEntityType

abstract class ContainerEntity<T>(override val name: String = "unknown",
                                  override val description: String = "", val contained: T) : BaseEntityType() {
}