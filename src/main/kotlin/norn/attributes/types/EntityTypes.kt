package norn.attributes.types

import org.hexworks.amethyst.api.base.BaseEntityType

// TODO revamp stats
object Player : BaseEntityType(
    name = "player"
), Interactor, Combatant, ItemHolder, EnergyUser, EquipmentHolder, ExperienceGainer, ZirconHolder

object Placeholder : BaseEntityType(name = "Placeholder"), Combatant

object Wall : BaseEntityType(
    name = "wall",
    description = "It's a wall."
), Interactor

object Fungus : BaseEntityType(
    name = "fungus",
    description = "This fungus  grows a lot."
), Combatant, Interactor

object StairsDown : BaseEntityType(
    name = "stairs down",
    description = "One step closer."
), Interactor

object StairsUp : BaseEntityType(
    name = "stairs up",
    description = "Backtracking, eh?"
), Interactor

object FogOfWarType : BaseEntityType()

object Bat : BaseEntityType(
    name = "bat",
    description = "Why is it always bats?"
), Combatant, ItemHolder, Interactor

object Zombie : BaseEntityType(
    name = "zombie",
    description = "Kill it! Kill it!"
), Combatant, ItemHolder, Interactor

object Zircon : BaseEntityType(
    name = "Zircon",
    description = "A small piece of Zircon. Its value is unfathomable."
), Item, Interactor

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

object Lorestone : BaseEntityType(
    name = "Lorestone",
    description = "It has lore"
)