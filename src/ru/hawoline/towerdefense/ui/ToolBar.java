package ru.hawoline.towerdefense.ui;

import ru.hawoline.towerdefense.GameState;
import ru.hawoline.towerdefense.object.Tile;
import ru.hawoline.towerdefense.scene.Editing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ToolBar extends Bar {
    private MyButton buttonGoToMenu;
    private MyButton buttonSave;
    private ArrayList<MyButton> tiles = new ArrayList<>();
    ;
    private Tile selectedTile;
    private Editing editing;

    public ToolBar(int x, int y, int width, int height, Editing editing) {
        super(x, y, width, height);
        this.editing = editing;
        initButtons();
    }

    private void initButtons() {
        buttonGoToMenu = new MyButton("Menu", 2, 642, 100, 30);
        buttonSave = new MyButton("Save", 2, 675, 100, 30);
        int width = 50;
        int height = 50;
        int xStart = 110;
        int yStart = 650;
        int xOffset = (int) (width * 1.1f);

        int i = 0;
        for (Tile tile: editing.getTileManager().tiles) {
            tiles.add(new MyButton(tile.getName(), xStart + xOffset * i, yStart, width, height, i));
            i++;
        }
    }

    public void draw(Graphics graphics) {
        graphics.setColor(Color.ORANGE);
        graphics.fillRect(getX(), getY(), getWidth(), getHeight());

        drawButtons(graphics);
        drawSelectedTile(graphics);
    }

    public void drawButtons(Graphics graphics) {
        buttonSave.draw(graphics);
        buttonGoToMenu.draw(graphics);
        drawTileButtons(graphics);
    }


    private void drawSelectedTile(Graphics graphics) {
        if (selectedTile != null) {
            graphics.drawImage(selectedTile.getSprite(), 550, 650, 50, 50, null);
        }
    }

    private void drawTileButtons(Graphics graphics) {
        for (MyButton button: tiles) {
            graphics.drawImage(getButtonImage(button.getId()), button.getX(), button.getY(), button.getWidth(),
                    button.getHeight(), null);

            if (button.isMouseOver()) {
                graphics.setColor(Color.WHITE);
                graphics.drawRect(button.getX(), button.getY(), button.getWidth(), button.getHeight());
            }

            if (button.isMousePressed()) {
                graphics.drawRect(button.getX() + 1, button.getY() + 1, button.getWidth() - 2, button.getHeight() - 2);
            }
        }
    }

    private BufferedImage getButtonImage(int id) {
        return editing.getTileManager().getSprite(id);
    }

    public void mouseClicked(int x, int y) {
        if (buttonGoToMenu.getBounds().contains(x, y)) {
            editing.getGame().setGameScene(GameState.MENU);
        } else if (buttonSave.getBounds().contains(x, y)) {
            saveLevel();
        } else {
            for (MyButton button: tiles) {
                boolean isPressed = button.getBounds().contains(x, y);
                button.setMousePressed(isPressed);
                if (isPressed) {
                    selectedTile = editing.getTileManager().getTile(button.getId());
                    editing.setSelectedTile(selectedTile);
                    break;
                }
            }
        }
    }

    public void mouseMoved(int x, int y) {
        buttonGoToMenu.setMouseOver(buttonGoToMenu.getBounds().contains(x, y));
        buttonSave.setMouseOver(buttonSave.getBounds().contains(x, y));
        for (MyButton button: tiles) {
            button.setMouseOver(button.getBounds().contains(x, y));
        }
    }

    private void saveLevel() {
        editing.saveLevel();
    }

}
