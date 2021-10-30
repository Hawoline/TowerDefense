package ru.hawoline.towerdefense.scene;

import ru.hawoline.towerdefense.Game;
import ru.hawoline.towerdefense.manager.TileManager;
import ru.hawoline.towerdefense.ui.ActionBar;
import ru.hawoline.towerdefense.util.LoadSave;

import java.awt.*;

public class Playing extends GameScene {
    private int[][] level;
    private ActionBar actionBar;

    public Playing(Game game) {
        super(game);

        actionBar = new ActionBar(0, 640, 640, 100, this);
        createDefaultLevel();
    }

    public void createDefaultLevel() {
        level = LoadSave.readLevel("FirstLevel.txt");
    }

    @Override
    public void render(Graphics graphics) {
        for (int y = 0; y < level.length; y++) {
            for (int x = 0; x < level[y].length; x++) {
                int id = level[y][x];
                graphics.drawImage(getGame().getTileManager().getSprite(id), x * 32, y * 32, null);
            }
        }
        actionBar.draw(graphics);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (y > 640) {
            actionBar.mouseClicked(x, y);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (y > 640) {
            actionBar.mouseMoved(x, y);
        }
    }

    @Override
    public void mouseDragged(int x, int y) {

    }

    public TileManager getTileManager() {
        return getGame().getTileManager();
    }
}
