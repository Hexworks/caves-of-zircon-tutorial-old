package org.hexworks.cavesofzircon

import org.hexworks.cavesofzircon.view.StartView
import org.hexworks.zircon.api.SwingApplications

fun main() {
    SwingApplications.startApplication(GameConfig.buildAppConfig()).dock(StartView())
}
