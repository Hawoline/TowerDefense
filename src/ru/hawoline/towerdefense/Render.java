package ru.hawoline.towerdefense;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Render {
    private GameScreen gameScreen;
    private BufferedImage bufferedImage;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();

    public Render(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        importImages();
        loadSprites();
    }

    public void render(Graphics graphics) {
        switch (GameState.gameState) {
            case MENU:
                break;
            case PLAYING:
                break;
            case SETTINGS:
                break;
        }
    }

    private void importImages() {
        InputStream inputStream = getClass().getResourceAsStream("res/spriteatlas.png");

        try {
            bufferedImage = ImageIO.read(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadSprites() {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                sprites.add(bufferedImage.getSubimage(x * 32, y * 32, 32, 32));
            }
        }
    }
}
