package ru.hawoline.towerdefense;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Game extends JFrame {
    private GameScreen gameScreen;
    private BufferedImage bufferedImage;

    public Game() {
        setSize(640, 640);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        importImages();
        gameScreen = new GameScreen(bufferedImage);
        add(gameScreen);
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

    public static void main(String[] args) {
        Game game = new Game();
    }
}
