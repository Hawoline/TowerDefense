package ru.hawoline.towerdefense.ui;

import ru.hawoline.towerdefense.GameState;
import ru.hawoline.towerdefense.object.Tile;
import ru.hawoline.towerdefense.scene.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BottomBar {
    private int x;
    private int y;
    private int width;
    private int height;
    private Playing playing;

    private MyButton buttonGoToMenu;
    private ArrayList<MyButton> tiles;

    public BottomBar(int x, int y, int width, int height, Playing playing) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.playing = playing;
        tiles = new ArrayList<>();

        initButtons();
    }

    private void initButtons() {
        buttonGoToMenu = new MyButton("Menu", 2, 642, 100, 30);
        int width = 50;
        int height = 50;
        int xStart = 110;
        int yStart = 650;
        int xOffset = (int) (width * 1.1f);

        int i = 0;
        for (Tile tile: playing.getTileManager().tiles) {
            tiles.add(new MyButton(tile.getName(), xStart + xOffset * i, yStart, width, height, i));
            i++;
        }
    }

    public void draw(Graphics graphics) {
        graphics.setColor(Color.ORANGE);
        graphics.fillRect(x, y, width, height);

        drawButtons(graphics);
    }

    public void drawButtons(Graphics graphics) {
        buttonGoToMenu.draw(graphics);

        drawTileButtons(graphics);
    }

    private void drawTileButtons(Graphics graphics) {
        for (MyButton button: tiles) {
            graphics.drawImage(getButtonImage(button.getId()), button.getX(), button.getY(), button.getWidth(),
                    button.getHeight(), null);
        }
    }

    private BufferedImage getButtonImage(int id) {
        return playing.getTileManager().getSprite(id);
    }

    public void mouseClicked(int x, int y) {
        if (buttonGoToMenu.getBounds().contains(x, y)) {
            GameState.setGameState(GameState.MENU);
        }
    }

    public void mouseMoved(int x, int y) {
        buttonGoToMenu.setMouseOver(buttonGoToMenu.getBounds().contains(x, y));
    }
}
