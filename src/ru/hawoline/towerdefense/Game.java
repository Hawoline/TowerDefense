package ru.hawoline.towerdefense;

import ru.hawoline.towerdefense.scene.Menu;
import ru.hawoline.towerdefense.scene.Playing;
import ru.hawoline.towerdefense.scene.Settings;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class Game extends JFrame implements Runnable {
    private GameScreen gameScreen;
    private BufferedImage bufferedImage;
    private long lastFrame;
    private long lastUpdate;
    private int updates = 0;
    private long lastTimeUpdatePerSecond;
    private Thread gameThread;

    private Render render;
    private Playing playing;
    private Menu menu;
    private Settings settings;

    private static final long TIME_PER_FRAME = 1000 / 120;
    private static final long TIME_PER_UPDATE = 1000 / 60;

    public Game() {
        initScenesAndRender();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        gameScreen = new GameScreen(this);
        add(gameScreen);
        pack();
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void initScenesAndRender() {
        render = new Render(this);
        gameScreen = new GameScreen(this);
        menu = new Menu(this);
        playing = new Playing(this);
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

    public Render getRender() {
        return render;
    }

    public Playing getPlaying() {
        return playing;
    }

    public Menu getMenu() {
        return menu;
    }

    public Settings getSettings() {
        return settings;
    }
}
