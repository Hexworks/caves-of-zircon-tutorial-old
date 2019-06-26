package org.hexworks.cavesofzircon.view.dialog

import org.hexworks.cavesofzircon.GameConfig
import org.hexworks.zircon.api.builder.component.ModalBuilder
import org.hexworks.zircon.api.component.Container
import org.hexworks.zircon.api.component.modal.Modal
import org.hexworks.zircon.api.component.modal.ModalFragment
import org.hexworks.zircon.api.screen.Screen
import org.hexworks.zircon.internal.component.modal.EmptyModalResult

abstract class Dialog(private val screen: Screen,
                      withClose: Boolean = true) : ModalFragment<EmptyModalResult> {

    abstract val container: Container                           // 1

    final override val root: Modal<EmptyModalResult> by lazy {  // 2
        ModalBuilder.newBuilder<EmptyModalResult>()
                .withComponent(container)
                .withParentSize(screen.size)
                .withCenteredDialog(true)                       // 3
                .build().also {
                    if (withClose) {                            // 4
                        container.addFragment(CloseButtonFragment(it, container))
                    }
                    container.applyColorTheme(GameConfig.THEME)
                }
    }
}
