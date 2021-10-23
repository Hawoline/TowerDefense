package ru.hawoline.towerdefense.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_A) {
            System.out.println("A is pressed!");
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
