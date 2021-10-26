package ru.hawoline.towerdefense.listener;

import ru.hawoline.towerdefense.Game;
import ru.hawoline.towerdefense.GameState;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MyMouseListener implements MouseListener, MouseMotionListener {
    private Game game;

    public MyMouseListener(Game game) {
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
            switch (GameState.gameState) {
                case MENU:
                    game.getMenu().mouseClicked(mouseEvent.getX(), mouseEvent.getY());
                    System.out.println("dlsvmlvm" + mouseEvent.getX() + " " + mouseEvent.getY());
                    break;
                case PLAYING:
                    game.getPlaying().mouseClicked(mouseEvent.getX(), mouseEvent.getY());

                    break;
                case SETTINGS:
                    break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        switch (GameState.gameState) {
            case MENU:
                game.getMenu().mouseMoved(mouseEvent.getX(), mouseEvent.getY());
                System.out.println("dlsvmlvm" + mouseEvent.getX() + " " + mouseEvent.getY());
                break;
            case PLAYING:
                game.getPlaying().mouseMoved(mouseEvent.getX(), mouseEvent.getY());
                break;
            case SETTINGS:
                break;
        }
    }
}
