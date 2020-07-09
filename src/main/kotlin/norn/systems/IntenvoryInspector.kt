package norn.systems

import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType
import norn.GameConfig
import norn.attributes.types.*
import norn.commands.DropItem
import norn.commands.Eat
import norn.commands.InspectInventory
import norn.extensions.GameCommand
import norn.extensions.GameItem
import norn.extensions.whenTypeIs
import norn.view.dialog.ExamineDialog
import norn.view.fragment.InventoryFragment
import norn.world.GameContext
import org.hexworks.cobalt.datatypes.Maybe
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.Sizes
import org.hexworks.zircon.api.builder.component.ModalBuilder
import org.hexworks.zircon.api.component.ComponentAlignment.BOTTOM_LEFT
import org.hexworks.zircon.api.extensions.onComponentEvent
import org.hexworks.zircon.api.uievent.ComponentEventType.ACTIVATED
import org.hexworks.zircon.api.uievent.Processed
import org.hexworks.zircon.internal.component.modal.EmptyModalResult

object InventoryInspector : BaseFacet<GameContext>() {

    val DIALOG_SIZE = Sizes.create(40, 14)

    override fun executeCommand(command: GameCommand<out EntityType>) = command
            .responseWhenCommandIs(InspectInventory::class) { (context, itemHolder, position) ->

                val screen = context.screen

                val panel = Components.panel()
                        .withSize(DIALOG_SIZE)
                        .wrapWithBox(true)
                        .wrapWithShadow(true)
                        .withTitle("Inventory")
                        .build()

                val fragment = InventoryFragment(
                        inventory = itemHolder.inventory,
                        width = DIALOG_SIZE.width - 3,
                        onDrop = { item ->
                            itemHolder.executeCommand(DropItem(context, itemHolder, item, position))
                        },
                        onEat = { item ->
                            itemHolder.whenTypeIs<EnergyUser> { eater ->
                                item.whenTypeIs<Food> { food ->
                                    itemHolder.inventory.removeItem(food)
                                    itemHolder.executeCommand(Eat(context, eater, food))
                                }
                            }
                        },
                        onEquip = { item ->
                            var result = Maybe.empty<GameItem>()
                            itemHolder.whenTypeIs<EquipmentHolder> { equipmentHolder ->
                                item.whenTypeIs<CombatItem> { combatItem ->
                                    result = Maybe.of(equipmentHolder.equip(itemHolder.inventory, combatItem))
                                }
                            }
                            result
                        },
                        onExamine = { item ->
                            screen.openModal(ExamineDialog(screen, item))
                        })

                panel.addFragment(fragment)

                val modal = ModalBuilder.newBuilder<EmptyModalResult>()
                        .withParentSize(screen.size)
                        .withComponent(panel)
                        .build()

                panel.addComponent(Components.button()
                        .withText("Close")
                        .withAlignmentWithin(panel, BOTTOM_LEFT)
                        .build().apply {
                            onComponentEvent(ACTIVATED) {
                                modal.close(EmptyModalResult)
                                Processed
                            }
                        })

                modal.applyColorTheme(GameConfig.THEME)
                screen.openModal(modal)
                Consumed
            }

}
