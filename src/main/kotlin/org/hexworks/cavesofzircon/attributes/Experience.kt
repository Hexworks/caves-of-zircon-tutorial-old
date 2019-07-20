package org.hexworks.cavesofzircon.attributes

import org.hexworks.cobalt.databinding.api.createPropertyFrom
import org.hexworks.cobalt.databinding.api.expression.concat
import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.zircon.api.Components

data class Experience(val currentXPProperty: Property<Int> = createPropertyFrom(0),
                      val currentLevelProperty: Property<Int> = createPropertyFrom(1)) : DisplayableAttribute {

    var currentXP: Int by currentXPProperty.asDelegate()
    var currentLevel: Int by currentLevelProperty.asDelegate()

    constructor(
        currentXP: Int,
        currentLevel: Int
    ): this(
        createPropertyFrom(currentXP),
        createPropertyFrom(currentLevel)
    )

    override fun toComponent(width: Int) = Components.vbox()
            .withSize(width, 3)
            .build().apply {
                val xpLabel = Components.label()
                        .withSize(width, 1)
                        .build()
                val levelLabel = Components.label()
                        .withSize(width, 1)
                        .build()

                xpLabel.textProperty.updateFrom(createPropertyFrom("XP:  ")
                        .concat(currentXPProperty))

                levelLabel.textProperty.updateFrom(createPropertyFrom("Lvl: ")
                        .concat(currentLevelProperty))

                addComponent(Components.textBox()
                        .withContentWidth(width)
                        .addHeader("Experience", false))
                addComponent(xpLabel)
                addComponent(levelLabel)
            }
}
