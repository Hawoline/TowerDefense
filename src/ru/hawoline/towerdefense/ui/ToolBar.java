package ru.hawoline.towerdefense.ui;

import ru.hawoline.towerdefense.GameState;
import ru.hawoline.towerdefense.object.Tile;
import ru.hawoline.towerdefense.scene.Editing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ToolBar extends Bar {
    private CustomButton buttonGoToMenu;
    private CustomButton buttonSave;
//    private ArrayList<CustomButton> tiles = new ArrayList<>();
    private Tile selectedTile;
    private Editing editing;

    private Map<CustomButton, ArrayList<Tile>> tileButtons = new HashMap<>();

    private CustomButton buttonGrass;
    private CustomButton buttonWater;
    private CustomButton buttonRoadStraight;
    private CustomButton buttonRoadCorner;
    private CustomButton buttonWaterCorner;
    private CustomButton buttonWaterBeaches;
    private CustomButton buttonWaterIslands;

    public ToolBar(int x, int y, int width, int height, Editing editing) {
        super(x, y, width, height);
        this.editing = editing;
        initButtons();
    }

    private void initButtons() {
        buttonGoToMenu = new CustomButton("Menu", 2, 642, 100, 30);
        buttonSave = new CustomButton("Save", 2, 675, 100, 30);
        int width = 50;
        int height = 50;
        int xStart = 110;
        int yStart = 650;
        int xOffset = (int) (width * 1.1f);
        int i = 0;

        buttonGrass = new CustomButton("Grass", xStart, yStart, width, height, i++);
        buttonWater = new CustomButton("Water", xStart + xOffset, yStart, width, height, i++);

        initTileButton(buttonRoadStraight, editing.getTileManager().getStraightRoads(), xStart, yStart, xOffset, width, height, i++);
        initTileButton(buttonRoadCorner, editing.getTileManager().getStraightRoads(), xStart, yStart, xOffset, width, height, i++);
        initTileButton(buttonWaterCorner, editing.getTileManager().getWaterCorners(), xStart, yStart, xOffset, width, height, i++);
        initTileButton(buttonWaterBeaches, editing.getTileManager().getBeaches(), xStart, yStart, xOffset, width, height, i++);
        initTileButton(buttonWaterIslands, editing.getTileManager().getIslands(), xStart, yStart, xOffset, width, height, i++);
    }

    private void initTileButton(CustomButton button, ArrayList<Tile> list, int x, int y, int xOffset, int width, int height, int id) {
        button = new CustomButton("", x + xOffset * id, y, width, height, id);
        tileButtons.put(button, list);
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
        drawNormalButton(graphics, buttonGrass);
        drawNormalButton(graphics, buttonWater);
        drawMapButtons(graphics);
    }

    private void drawNormalButton(Graphics graphics, CustomButton button) {
        graphics.drawImage(getButtonImage(button.getId()), button.getX(), button.getY(), button.getWidth(),
                button.getHeight(), null);

        drawButtonFeedback(graphics, button);
    }

    private void drawMapButtons(Graphics graphics) {
        for (Map.Entry<CustomButton, ArrayList<Tile>> entry: tileButtons.entrySet()) {
            CustomButton button = entry.getKey();
            BufferedImage image = entry.getValue().get(0).getSprite();

            graphics.drawImage(image, button.getX(), button.getY(), button.getWidth(), button.getHeight(), null);
            drawButtonFeedback(graphics, button);
        }
    }

    private void drawButtonFeedback(Graphics graphics, CustomButton button) {
        if (button.isMouseOver()) {
            graphics.setColor(Color.WHITE);
        } else {
            graphics.setColor(Color.BLACK);
        }
        graphics.drawRect(button.getX(), button.getY(), button.getWidth(), button.getHeight());

        if (button.isMousePressed()) {
            graphics.drawRect(button.getX() + 1, button.getY() + 1, button.getWidth() - 2, button.getHeight() - 2);
        }
    }


    private void drawSelectedTile(Graphics graphics) {
        if (selectedTile != null) {
            graphics.drawImage(selectedTile.getSprite(), 550, 650, 50, 50, null);
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
            buttonWater.setMouseOver(buttonWater.getBounds().contains(x, y));
            boolean isPressed = buttonWater.getBounds().contains(x, y);
            buttonWater.setMousePressed(isPressed);
            if (isPressed) {
//                selectedTile = buttonWater;
                editing.setSelectedTile(selectedTile);
            }
            for (CustomButton button: tileButtons.keySet()) {
                boolean buttonIsPressed = button.getBounds().contains(x, y);
                button.setMousePressed(buttonIsPressed);
                if (buttonIsPressed) {
                    selectedTile = tileButtons.get(button).get(0);
                    editing.setSelectedTile(selectedTile);
                    break;
                }
            }
        }
    }

    public void mouseMoved(int x, int y) {
        buttonGoToMenu.setMouseOver(buttonGoToMenu.getBounds().contains(x, y));
        buttonSave.setMouseOver(buttonSave.getBounds().contains(x, y));

        for (CustomButton button: tileButtons.keySet()) {
            button.setMouseOver(button.getBounds().contains(x, y));
        }
    }

    public void mousePressed(int x, int y) {
        buttonGoToMenu.setMousePressed(buttonGoToMenu.getBounds().contains(x, y));
        buttonSave.setMousePressed(buttonSave.getBounds().contains(x, y));

        for (CustomButton button: tileButtons.keySet()) {
            button.setMousePressed(button.getBounds().contains(x, y));
        }
    }

    public void mouseReleased(int x, int y) {
        buttonGoToMenu.resetBooleans();
        buttonSave.resetBooleans();
        for (CustomButton button: tileButtons.keySet()) {
            button.resetBooleans();
        }
    }

    private void saveLevel() {
        editing.saveLevel();
    }

}
