package norn

import norn.view.PlayView
import norn.view.StartView
import norn.world.MetaContext
import org.hexworks.zircon.api.SwingApplications

fun main(args: Array<String>) {
    var mode = if (args.isNotEmpty()) {
        RunMode.valueOf(args[0])
    } else {
        println("defaulting to player run mode")
        RunMode.PLAYER
    }

    GameConfig.runMode = mode
    MetaContext.runMode = mode


    val application = SwingApplications.startApplication(GameConfig.buildAppConfig())

    application.dock(PlayView())

}
