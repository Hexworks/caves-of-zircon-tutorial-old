package norn.view.dialog

import norn.attributes.CombatStats
import norn.attributes.Vision
import norn.attributes.types.Player
import norn.extensions.GameEntity
import norn.extensions.tryToFindAttribute
import norn.functions.logGameEvent
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.extensions.onComponentEvent
import org.hexworks.zircon.api.graphics.BoxType
import org.hexworks.zircon.api.screen.Screen
import org.hexworks.zircon.api.uievent.ComponentEventType
import org.hexworks.zircon.api.uievent.Processed
import org.hexworks.zircon.internal.component.modal.EmptyModalResult

class LevelUpDialog(screen: Screen, player: GameEntity<Player>) : Dialog(screen, false) {

    override val container = Components.vbox()
        .withTitle("Ding!")
        .withSize(30, 15)
        .withBoxType(BoxType.TOP_BOTTOM_DOUBLE)
        .wrapWithBox()
        .build().apply {
            val stats = player.tryToFindAttribute(CombatStats::class)
            val vision = player.tryToFindAttribute(Vision::class)

            addComponent(
                Components.textBox()
                    .withContentWidth(27)
                    .addHeader("Congratulations, you leveled up!")
                    .addParagraph("Pick an improvement from the options below:")
            )

            addComponent(Components.button()
                .withText("Max HP")
                .build().apply {
                    onComponentEvent(ComponentEventType.ACTIVATED) {
                        stats.maxHpProperty.value += 10
                        logGameEvent("You look healthier.")
                        root.close(EmptyModalResult)
                        Processed
                    }
                })


            addComponent(Components.button()
                .withText("Attack")
                .build().apply {
                    onComponentEvent(ComponentEventType.ACTIVATED) {
                        stats.attackValueProperty.value += 2
                        logGameEvent("You look stronger.")
                        root.close(EmptyModalResult)
                        Processed
                    }
                })

            addComponent(Components.button()
                .withText("Defense")
                .build().apply {
                    onComponentEvent(ComponentEventType.ACTIVATED) {
                        stats.defenseValueProperty.value += 2
                        logGameEvent("You look tougher.")
                        root.close(EmptyModalResult)
                        Processed
                    }
                })

            addComponent(Components.button()
                .withText("Vision")
                .build().apply {
                    onComponentEvent(ComponentEventType.ACTIVATED) {
                        vision.radius++
                        logGameEvent("You look more perceptive.")
                        root.close(EmptyModalResult)
                        Processed
                    }
                })
        }
}
