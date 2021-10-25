package ru.hawoline.towerdefense.scene;

import ru.hawoline.towerdefense.Game;
import ru.hawoline.towerdefense.GameState;
import ru.hawoline.towerdefense.manager.TileManager;
import ru.hawoline.towerdefense.ui.MyButton;
import ru.hawoline.towerdefense.util.LevelBuilder;

import java.awt.*;

public class Playing extends GameScene {
    private int[][] level;
    private TileManager tileManager;
    private MyButton buttonGoToMenu;

    public Playing(Game game) {
        super(game);
        level = LevelBuilder.getLevelData();
        tileManager = new TileManager();
        buttonGoToMenu = new MyButton("Menu", 0, 0, 100, 30);
    }

    @Override
    public void render(Graphics graphics) {
        for (int y = 0; y < level.length; y++) {
            for (int x = 0; x < level[y].length; x++) {
                int id = level[y][x];
                graphics.drawImage(tileManager.getSprite(id), x * 32, y * 32, null);
            }
        }
        buttonGoToMenu.draw(graphics);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (buttonGoToMenu.getBounds().contains(x, y)) {
            GameState.setGameState(GameState.MENU);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {

    }
}
