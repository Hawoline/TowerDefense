package ru.hawoline.towerdefense.scene;

import ru.hawoline.towerdefense.Game;
import ru.hawoline.towerdefense.GameState;
import ru.hawoline.towerdefense.ui.MyButton;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Menu extends GameScene {
    private BufferedImage image;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();

    private MyButton buttonPlaying;
    private MyButton buttonSettings;
    private MyButton buttonQuit;

    public Menu(Game game) {
        super(game);
        initButtons();
    }

    @Override
    public void render(Graphics graphics) {
        drawButtons(graphics);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (buttonPlaying.getBounds().contains(x, y)) {
            GameState.setGameState(GameState.PLAYING);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        buttonPlaying.setMouseOver(buttonPlaying.getBounds().contains(x, y));
    }

    private void drawButtons(Graphics graphics) {
        buttonPlaying.draw(graphics);
    }

    private void initButtons() {
        buttonPlaying = new MyButton("Play", 100, 100, 100, 30);

    }
}
