package norn.world

enum class GameState(val type: String) {
    PLAYER_TURN("playerTurn"),
    NPC_TURN("npcTurn"),
    TARGETING("targeting"),
    PREGAME("pregame"),
    MENU("menu"),
    LOSE("lose"),
    WIN("win")
}