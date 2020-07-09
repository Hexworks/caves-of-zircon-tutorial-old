package norn.view.fragment

import norn.attributes.DisplayableAttribute
import norn.attributes.types.Player
import norn.extensions.GameEntity
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.Fragment

class PlayerStatsFragment(
        width: Int,
        player: GameEntity<Player>) : Fragment {

    override val root = Components.vbox()
            .withSize(width, 30)
            .withSpacing(1)
            .build().apply {
                addComponent(Components.header().withText("Player"))
                player.attributes.toList().filterIsInstance<DisplayableAttribute>()
                        .forEach {
                            addComponent(it.toComponent(width))
                        }
            }
}
