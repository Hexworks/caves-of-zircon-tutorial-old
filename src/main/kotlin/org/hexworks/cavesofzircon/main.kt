package org.hexworks.cavesofzircon

import org.hexworks.zircon.api.ColorThemes
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.Screens
import org.hexworks.zircon.api.SwingApplications
import org.hexworks.zircon.api.component.ComponentAlignment

@Suppress("ConstantConditionIf")
fun main(args: Array<String>) {

    val grid = SwingApplications.startTileGrid()
    val screen = Screens.createScreenFor(grid)

    screen.addComponent(Components.header()
            .withText("Hello, from Caves of Zircon!")
            .withAlignmentWithin(screen, ComponentAlignment.CENTER))

    screen.applyColorTheme(ColorThemes.arc())
    screen.display()

}
