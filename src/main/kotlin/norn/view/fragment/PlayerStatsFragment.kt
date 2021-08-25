package norn.view.fragment

import norn.GameConfig
import norn.RunMode
import norn.attributes.DisplayableAttribute
import norn.attributes.types.Player
import norn.extensions.GameEntity
import norn.functions.logDevGameEvent
import norn.world.GameState
import norn.world.MetaContext
import org.hexworks.cobalt.databinding.api.createPropertyFrom
import org.hexworks.cobalt.databinding.api.expression.concat
import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.Fragment

class PlayerStatsFragment(
    width: Int,
    player: GameEntity<Player>
) : Fragment {

    private val gameStateProperty: Property<GameState> = createPropertyFrom(MetaContext.gameState)
    private val gameState: GameState by gameStateProperty.asDelegate()

    init {
        gameStateProperty.bind(MetaContext.gameStateProperty)
    }

    override val root = Components.vbox()
        .withSize(width, 30)
        .withSpacing(1)
        .build().apply {
            addComponent(Components.header().withText("Player"))
            player.attributes.toList().filterIsInstance<DisplayableAttribute>()
                .forEach {
                    addComponent(it.toComponent(width))
                }

            if (GameConfig.runMode == RunMode.DEV || GameConfig.runMode == RunMode.DEBUG) {
                val gameStateLabel = Components.label()
                    .withSize(width, 1)
                    .build()

                gameStateLabel.textProperty.updateFrom(
                    createPropertyFrom("")
                        .concat(gameState.toString())
                )

                gameStateProperty.onChange {
                    gameStateLabel.textProperty.value = gameState.toString()
                }

                addComponent(gameStateLabel)
            }
        }
}
