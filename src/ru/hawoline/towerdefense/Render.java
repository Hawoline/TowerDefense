package ru.hawoline.towerdefense;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Render {
    private Game game;
    private BufferedImage bufferedImage;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();

    public Render(Game game) {
        this.game = game;
    }

    public void render(Graphics graphics) {
        switch (GameState.gameState) {
            case MENU:
                game.getMenu().render(graphics);
                break;
            case PLAYING:
                game.getPlaying().render(graphics);
                break;
            case SETTINGS:
                game.getSettings().render(graphics);
                break;
        }
    }
}
