package ru.hawoline.towerdefense.scene;

import ru.hawoline.towerdefense.Game;
import ru.hawoline.towerdefense.manager.TileManager;
import ru.hawoline.towerdefense.object.Tile;
import ru.hawoline.towerdefense.ui.ToolBar;
import ru.hawoline.towerdefense.util.LevelBuilder;
import ru.hawoline.towerdefense.util.LoadSave;

import java.awt.*;

public class Editing extends GameScene {
    private int[][] level;
    private int mouseX;
    private int mouseY;

    private TileManager tileManager;
    private Tile selectedTile;
    private ToolBar toolbar;

    public Editing(Game game) {
        super(game);
        tileManager = new TileManager();
        toolbar = new ToolBar(0, 640, 640, 100, this);
        createDefaultLevel();
    }

    @Override
    public void render(Graphics graphics) {
        drawSelectedTile(graphics);
        toolbar.draw(graphics);
        drawLevel(graphics);
    }

    private void drawLevel(Graphics graphics) {
        for (int y = 0; y < level.length; y++) {
            for (int x = 0; x < level[y].length; x++) {
                int id = level[y][x];
                graphics.drawImage(getGame().getTileManager().getSprite(id), x * 32, y * 32, null);
            }
        }
    }

    private void createDefaultLevel() {
        level = LoadSave.readLevel("FirstLevel.txt");
    }

    @Override
    public void mouseClicked(int x, int y) {
        changeTile(x, y);
        toolbar.mouseClicked(x, y);
    }

    @Override
    public void mouseMoved(int x, int y) {
        mouseX = x;
        mouseY = y;
        toolbar.mouseMoved(x, y);
    }

    @Override
    public void mouseDragged(int x, int y) {
        changeTile(x, y);
    }

    private void drawSelectedTile(Graphics graphics) {
        if (selectedTile != null) {
            graphics.drawImage(selectedTile.getSprite(), mouseX, mouseY, 32, 32, null);
        }
    }

    private void changeTile(int x, int y) {
        if (selectedTile == null) {
            return;
        }
        if (y < 640) {
            int xCoordinate = x / 32;
            int yCoordinate = y / 32;
            level[yCoordinate][xCoordinate] = selectedTile.getId();
        }
    }

    public void saveLevel() {
        LoadSave.saveLevel("FirstLevel.txt", level);
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public void setSelectedTile(Tile selectedTile) {
        this.selectedTile = selectedTile;
    }
}
