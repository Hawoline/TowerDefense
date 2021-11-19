package ru.hawoline.towerdefense.listener;

import ru.hawoline.towerdefense.Game;
import ru.hawoline.towerdefense.GameState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardListener implements KeyListener {
    private Game game;

    public KeyBoardListener(Game game) {
        this.game = game;
    }
    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (GameState.currentState == GameState.EDITING) {
            game.getEditing().keyPressed(keyEvent);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
