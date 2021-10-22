package ru.hawoline.towerdefense;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Game extends JFrame implements Runnable {
    private GameScreen gameScreen;
    private BufferedImage bufferedImage;
    private long lastFrame;
    private long lastUpdate;
    private int updates = 0;
    private long lastTimeUpdatePerSecond;
    private Thread gameThread;

    private static final long TIME_PER_FRAME = 1000 / 120;
    private static final long TIME_PER_UPDATE = 1000 / 60;

    public Game() {
        setSize(640, 640);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        importImages();
        gameScreen = new GameScreen(bufferedImage);
        add(gameScreen);

        gameThread = new Thread(this);
        gameThread.start();
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

    private void loopGame() {
        while (true) {
            if (System.currentTimeMillis() - lastUpdate >= TIME_PER_UPDATE) {
                updateGame();
                callUpdatePerSecond();
            }
            if (System.currentTimeMillis() - lastFrame >= TIME_PER_FRAME) {
                lastFrame = System.currentTimeMillis();
                repaint();
            }
        }
    }

    private void updateGame() {
        updates++;
        lastUpdate = System.currentTimeMillis();
    }

    private void callUpdatePerSecond() {
        if (System.currentTimeMillis() - lastTimeUpdatePerSecond >= 1000) {
            System.out.println("UPS is " + updates++);
            updates = 0;
            lastTimeUpdatePerSecond = System.currentTimeMillis();
        }
    }

    @Override
    public void run() {
        loopGame();
    }

    public static void main(String[] args) {
        Game game = new Game();
    }
}
