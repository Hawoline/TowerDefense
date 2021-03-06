package ru.hawoline.towerdefense.scene;

import ru.hawoline.towerdefense.Game;
import ru.hawoline.towerdefense.manager.TileManager;
import ru.hawoline.towerdefense.object.Tile;
import ru.hawoline.towerdefense.ui.ToolBar;
import ru.hawoline.towerdefense.util.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Editing extends GameScene {
    private static final int ANIMATION_SPEED = 20;

    private int[][] level;
    private int mouseX;
    private int mouseY;

    private TileManager tileManager;
    private Tile selectedTile;
    private ToolBar toolbar;

    private boolean canDrawSelectedTile = false;
    private int animationIndex;
    private int tick;

    public Editing(Game game) {
        super(game);
        tick = 0;
        tileManager = new TileManager();
        toolbar = new ToolBar(0, 640, 640, 100, this);
        createDefaultLevel();
    }

    @Override
    public void render(Graphics graphics) {
        updateTick();
        toolbar.draw(graphics);
        drawLevel(graphics);
        drawSelectedTile(graphics);
    }

    private void updateTick() {
        tick++;
        if (tick >= ANIMATION_SPEED) {
            tick = 0;
            animationIndex++;
            if (animationIndex >= 4) {
                animationIndex = 0;
            }
        }
    }

    private void drawLevel(Graphics graphics) {
        for (int y = 0; y < level.length; y++) {
            for (int x = 0; x < level[y].length; x++) {
                int id = level[y][x];
                if (isAnimation(id)) {
                    graphics.drawImage(getSprite(id, animationIndex), x * 32, y * 32, null);
                } else {
                    graphics.drawImage(getGame().getTileManager().getSprite(id), x * 32, y * 32, null);
                }
            }
        }
    }

    private boolean isAnimation(int spriteId) {
        return getGame().getTileManager().isSpriteAnimation(spriteId);
    }

    public BufferedImage getSprite(int spriteId, int animationIndex) {
        return getGame().getTileManager().getAnimationSprite(spriteId, animationIndex);
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
        canDrawSelectedTile = true;
        mouseX = x;
        mouseY = y;
        if (y > 640 || x > 640) {
            canDrawSelectedTile = false;
            toolbar.mouseMoved(x, y);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        canDrawSelectedTile = false;
        if (y >= 640) {
            toolbar.mousePressed(x, y);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        if (y >= 640) {
            toolbar.mouseReleased(x, y);
        }
    }

    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_R) {
            toolbar.rotateSprite();
        }
    }

    @Override
    public void mouseDragged(int x, int y) {
        canDrawSelectedTile = false;
        changeTile(x, y);
    }

    private void drawSelectedTile(Graphics graphics) {
        if (selectedTile != null && canDrawSelectedTile) {
            graphics.drawImage(selectedTile.getSprite(), mouseX, mouseY, 32, 32, null);
        }
    }

    private void changeTile(int x, int y) {
        if (selectedTile == null) {
            return;
        }
        if (y < 640 && x < 640 && y > 0 && x > 0) {
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
        canDrawSelectedTile = true;
    }
}
