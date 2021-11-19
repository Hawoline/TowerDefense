package ru.hawoline.towerdefense;

public enum GameState {
    PLAYING, EDITING, MENU, SETTINGS;

    public static GameState currentState = MENU;
}
