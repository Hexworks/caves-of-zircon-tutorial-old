package norn

import norn.view.StartView
import org.hexworks.zircon.api.SwingApplications

fun main(args: Array<String>) {

    val application = SwingApplications.startApplication(GameConfig.buildAppConfig())

    application.dock(StartView())

}
