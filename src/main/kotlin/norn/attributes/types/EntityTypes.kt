package norn.attributes.types

import norn.commands.Interact
import org.hexworks.amethyst.api.base.BaseEntityType


abstract class InteractableEntityType(
    override val name: String = "unknown",
    override val description: String = ""
) : BaseEntityType() {
    open fun doInteraction(interaction: Interact) {}
                     }

abstract class Actor(override val name: String = "unknown",
                     override val description: String = "") : InteractableEntityType()

// TODO revamp stats
object Player : Actor(
    name = "player"
), Combatant, ItemHolder, EnergyUser, EquipmentHolder, ExperienceGainer, ZirconHolder

object Placeholder : BaseEntityType(name = "Placeholder"), Combatant

object Wall : BaseEntityType(
    name = "wall"
)

object Fungus : Actor(
    name = "fungus"
), Combatant

object StairsDown : BaseEntityType(
    name = "stairs down"
)

object StairsUp : BaseEntityType(
    name = "stairs up"
)

object FogOfWarType : BaseEntityType()

object Bat : Actor(
    name = "bat"
), Combatant, ItemHolder

object Zombie : Actor(
    name = "zombie"
), Combatant, ItemHolder

object Runestone : InteractableEntityType (
    name = "runestone",
    description = "A carving of ancient knowledge."
), InteractableEntity

object Zircon : BaseEntityType(
    name = "Zircon",
    description = "A small piece of Zircon. Its value is unfathomable."
), Item

object BatMeat : BaseEntityType(
    name = "Bat meat",
    description = "Stringy bat meat. It is edible, but not tasty."
), Food

object Dagger : BaseEntityType(
    name = "Rusty Dagger",
    description = "A small, rusty dagger made of some metal alloy."
), Weapon

object Sword : BaseEntityType(
    name = "Iron Sword",
    description = "A shiny sword made of iron. It is a two-hand weapon"
), Weapon

object Staff : BaseEntityType(
    name = "Wooden Staff",
    description = "A wooden staff made of birch. It has seen some use"
), Weapon

object LightArmor : BaseEntityType(
    name = "Leather Tunic",
    description = "A tunic made of rugged leather. It is very comfortable."
), Armor

object MediumArmor : BaseEntityType(
    name = "Chainmail",
    description = "A sturdy chainmail armor made of interlocking iron chains."
), Armor

object HeavyArmor : BaseEntityType(
    name = "Platemail",
    description = "A heavy and shiny platemail armor made of bronze."
), Armor

object Club : BaseEntityType(
    name = "Club",
    description = "A wooden club. It doesn't give you an edge over your opponent (haha)."
), Weapon

object Jacket : BaseEntityType(
    name = "Leather jacket",
    description = "Dirty and rugged jacket made of leather."
), Armor

object Exit : BaseEntityType(
    name = "exit"
)