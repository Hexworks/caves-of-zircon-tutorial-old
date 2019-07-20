package org.hexworks.cavesofzircon.world

data class PlayerDTO(
    val currentXP: Int,
    val currentLevel: Int,
    val visionRadius: Int,
    val combatStats: CombatStatsDTO,
    val inventorySize: Int,
    val energyLevel: Int
)

data class CombatStatsDTO(
    val maxHp: Int,
    val attackValue: Int,
    val defenseValue: Int
)