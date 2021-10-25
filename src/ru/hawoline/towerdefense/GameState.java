package ru.hawoline.towerdefense;

public enum GameState {
    PLAYING, MENU, SETTINGS;

    public static GameState gameState = MENU;

    public static void setGameState(GameState gameState) {
        GameState.gameState = gameState;
    }
}
