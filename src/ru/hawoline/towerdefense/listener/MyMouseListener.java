package ru.hawoline.towerdefense.listener;

import ru.hawoline.towerdefense.Game;

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
            game.getCurrentScene().mouseClicked(mouseEvent.getX(), mouseEvent.getY());
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
        game.getCurrentScene().mouseDragged(mouseEvent.getX(), mouseEvent.getY());
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        game.getCurrentScene().mouseMoved(mouseEvent.getX(), mouseEvent.getY());
    }
}
