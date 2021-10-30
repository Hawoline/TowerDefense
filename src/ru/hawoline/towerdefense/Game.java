package ru.hawoline.towerdefense;

import ru.hawoline.towerdefense.manager.TileManager;
import ru.hawoline.towerdefense.scene.*;

import javax.swing.*;

public class Game extends JFrame implements Runnable {
    private GameScreen gameScreen;
    private long lastFrame;
    private long lastUpdate;
    private int updates = 0;
    private long lastTimeUpdatePerSecond;
    private Thread gameThread;

    private Render render;
    private GameScene currentScene;
    private Playing playing;
    private Menu menu;
    private Settings settings;
    private Editing editing;

    private TileManager tileManager;

    private static final long TIME_PER_FRAME = 1000 / 120;
    private static final long TIME_PER_UPDATE = 1000 / 60;

    public Game() {
        initScenesAndRender();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tileManager = new TileManager();
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
        editing = new Editing(this);
        currentScene = menu;
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

    public TileManager getTileManager() {
        return tileManager;
    }

    public GameScene getCurrentScene() {
        return currentScene;
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

    public Editing getEditing() {
        return editing;
    }

    public void setGameScene(GameState gameState) {
        if (gameState == GameState.MENU) {
            currentScene = menu;
        } else if (gameState == GameState.EDITING) {
            currentScene = editing;
        } else if (gameState == GameState.PLAYING) {
            currentScene = playing;
        } else if (gameState == GameState.SETTINGS) {
            currentScene = settings;
        }
    }
}
