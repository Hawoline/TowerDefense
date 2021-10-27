package ru.hawoline.towerdefense.scene;

import ru.hawoline.towerdefense.Game;
import ru.hawoline.towerdefense.manager.TileManager;
import ru.hawoline.towerdefense.object.Tile;
import ru.hawoline.towerdefense.ui.BottomBar;
import ru.hawoline.towerdefense.util.LevelBuilder;

import java.awt.*;

public class Playing extends GameScene {
    private int[][] level;
    private int mouseX;
    private int mouseY;

    private TileManager tileManager;
    private Tile selectedTile;

    private BottomBar bottomBar;

    public Playing(Game game) {
        super(game);
        level = LevelBuilder.getLevelData();
        tileManager = new TileManager();
        bottomBar = new BottomBar(0, 640, 640, 100, this);
    }

    @Override
    public void render(Graphics graphics) {
        for (int y = 0; y < level.length; y++) {
            for (int x = 0; x < level[y].length; x++) {
                int id = level[y][x];
                graphics.drawImage(tileManager.getSprite(id), x * 32, y * 32, null);
            }
        }
        bottomBar.draw(graphics);
        if (selectedTile != null) {
            graphics.drawImage(selectedTile.getSprite(), mouseX, mouseY, 32, 32, null);
        }
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (y > 640) {
            bottomBar.mouseClicked(x, y);
        } else {
            changeTile(x, y);
        }
    }

    private void changeTile(int x, int y) {
        if (selectedTile == null) {
            return;
        }
        int xCoordinate = x / 32;
        int yCoordinate = y / 32;
        level[yCoordinate][xCoordinate] = selectedTile.getId();
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (y > 640) {
            bottomBar.mouseMoved(x, y);
        } else {
            mouseX = x;
            mouseY = y;
        }
    }

    @Override
    public void mouseDragged(int x, int y) {
        changeTile(x, y);
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public void setSelectedTile(Tile selectedTile) {
        this.selectedTile = selectedTile;
    }
}
