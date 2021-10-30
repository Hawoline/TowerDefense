package ru.hawoline.towerdefense.ui;

import ru.hawoline.towerdefense.GameState;
import ru.hawoline.towerdefense.scene.Playing;

import java.awt.*;

public class ActionBar extends Bar {
    private Playing playing;

    private MyButton buttonGoToMenu;

    public ActionBar(int x, int y, int width, int height, Playing playing) {
        super(x, y, width, height);
        this.playing = playing;

        initButtons();
    }

    private void initButtons() {
        buttonGoToMenu = new MyButton("Menu", 2, 642, 100, 30);
    }

    public void draw(Graphics graphics) {
        graphics.setColor(Color.ORANGE);
        graphics.fillRect(getX(), getY(), getWidth(), getHeight());

        drawButtons(graphics);
    }

    public void drawButtons(Graphics graphics) {
        buttonGoToMenu.draw(graphics);
    }

    public void mouseClicked(int x, int y) {
        if (buttonGoToMenu.getBounds().contains(x, y)) {
            playing.getGame().setGameScene(GameState.MENU);
        }
    }

    public void mouseMoved(int x, int y) {
        buttonGoToMenu.setMouseOver(buttonGoToMenu.getBounds().contains(x, y));
    }
}
