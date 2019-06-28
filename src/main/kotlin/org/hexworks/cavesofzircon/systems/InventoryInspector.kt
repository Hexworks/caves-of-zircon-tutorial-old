package org.hexworks.cavesofzircon.systems

import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cavesofzircon.GameConfig
import org.hexworks.cavesofzircon.attributes.types.CombatItem
import org.hexworks.cavesofzircon.attributes.types.EnergyUser
import org.hexworks.cavesofzircon.attributes.types.EquipmentHolder
import org.hexworks.cavesofzircon.attributes.types.Food
import org.hexworks.cavesofzircon.attributes.types.equip
import org.hexworks.cavesofzircon.attributes.types.inventory
import org.hexworks.cavesofzircon.commands.DropItem
import org.hexworks.cavesofzircon.commands.Eat
import org.hexworks.cavesofzircon.commands.InspectInventory
import org.hexworks.cavesofzircon.extensions.GameCommand
import org.hexworks.cavesofzircon.extensions.GameItem
import org.hexworks.cavesofzircon.extensions.whenTypeIs
import org.hexworks.cavesofzircon.view.dialog.ExamineDialog
import org.hexworks.cavesofzircon.view.fragment.InventoryFragment
import org.hexworks.cavesofzircon.world.GameContext
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

                val fragment = InventoryFragment(
                        inventory = itemHolder.inventory,
                        width = DIALOG_SIZE.width - 3,
                        onDrop = { item ->
                            itemHolder.executeCommand(DropItem(context, itemHolder, item, position))
                        },
                        onExamine = { item ->
                            screen.openModal(ExamineDialog(screen, item))
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
                        })

                val panel = Components.panel()
                        .withSize(DIALOG_SIZE)
                        .wrapWithBox(true)
                        .wrapWithShadow(true)
                        .withTitle("Inventory")
                        .build()

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
