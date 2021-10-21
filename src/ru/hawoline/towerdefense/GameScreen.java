package ru.hawoline.towerdefense;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends JPanel {
    private Random random;
    private BufferedImage bufferedImage;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();

    public GameScreen(BufferedImage bufferedImage) {
        random = new Random();
        this.bufferedImage = bufferedImage;

        loadSprites();
    }

    private void loadSprites() {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                sprites.add(bufferedImage.getSubimage(x * 32, y * 32, 32, 32));
            }
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

//        graphics.drawImage(sprites.get(19), 0, 0, null);
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                graphics.drawImage(sprites.get(random.nextInt(100)), x * 32, y * 32 , null);
            }
        }
    }
}
