package ru.hawoline.towerdefense;

public enum GameState {
    PLAYING, EDITING, MENU, SETTINGS;

    public static GameState gameState = MENU;

    public static void setGameState(GameState gameState) {
        GameState.gameState = gameState;
    }
}
