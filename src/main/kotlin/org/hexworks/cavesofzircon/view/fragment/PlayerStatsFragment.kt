package org.hexworks.cavesofzircon.view.fragment

import org.hexworks.cavesofzircon.attributes.DisplayableAttribute
import org.hexworks.cavesofzircon.attributes.types.Player
import org.hexworks.cavesofzircon.extensions.GameEntity
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.Fragment

class PlayerStatsFragment(
    width: Int,
    player: GameEntity<Player>?
) : Fragment {

    override val root =
        Components
            .vbox()
            .withSize(width, 30)
            .withSpacing(1)
            .build()
            .apply {
                addComponent(
                    Components
                        .header()
                        .withText(
                            player
                                ?.name
                                ?: "Player"
                        )
                )
                player
                    ?.attributes
                    ?.toList()
                    ?.filterIsInstance<DisplayableAttribute>()
                    ?.forEach {
                        addComponent(it.toComponent(width))
                    }
            }
}
