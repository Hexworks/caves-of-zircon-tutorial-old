package org.hexworks.cavesofzircon.attributes.types

import org.hexworks.amethyst.api.base.BaseEntityType

object Player : BaseEntityType(
        name = "player"), Combatant, ItemHolder, EnergyUser

object Wall : BaseEntityType(
        name = "wall")

object Fungus : BaseEntityType(
        name = "fungus"), Combatant

object Bat : BaseEntityType(
        name = "bat"), Combatant, ItemHolder

object BatMeat : BaseEntityType(
        name = "Bat meat",
        description = "Stringy bat meat. It is edible, but not tasty."), Food

object StairsDown : BaseEntityType(
        name = "stairs down")

object StairsUp : BaseEntityType(
        name = "stairs up")

object Zircon : BaseEntityType(
        name = "Zircon",
        description = "A small piece of Zircon. Its value is unfathomable."), Item

object FogOfWarType : BaseEntityType()
