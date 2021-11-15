package ru.hawoline.towerdefense.manager;

import ru.hawoline.towerdefense.object.Tile;
import ru.hawoline.towerdefense.util.ImageFix;
import ru.hawoline.towerdefense.util.LoadSave;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileManager {
    public Tile grass;
    public Tile water;
    public Tile road;
    public Tile bottomLeftWaterCorner;
    public Tile topLeftWaterCorner;
    public BufferedImage atlas;
    public ArrayList<Tile> tiles = new ArrayList<>();

    public TileManager() {
        loadAtlas();
        createTiles();
    }

    private void createTiles() {
        int id = 0;
        tiles.add(grass = new Tile(getSprite(9, 0), id++, "Grass"));
        tiles.add(water = new Tile(getSprite(0, 0), id++, "Water"));
        tiles.add(road = new Tile(getSprite(8, 0), id++, "Road"));
        tiles.add(bottomLeftWaterCorner = new Tile(ImageFix.buildImage(
                getImages(0, 0, 5, 0)), id++, "Bottom Right Water Corner"));
        tiles.add(topLeftWaterCorner = new Tile(ImageFix.getBuildRotateImage(
                getImages(0,0,5,0),
                90, 1), id++, "Top Left Water Corner"));
    }

    private BufferedImage[] getImages(int firstX, int firstY, int secondX, int secondY) {
        return new BufferedImage[] {getSprite(firstX, firstY), getSprite(secondX, secondY)};
    }

    private void loadAtlas() {
        atlas = LoadSave.getSpriteAtlas();
    }

    public Tile getTile(int id) {
        return tiles.get(id);
    }
    public BufferedImage getSprite(int id) {
        return getTile(id).getSprite();
    }
    public BufferedImage getSprite(int xCoordinate, int yCoordinate) {
        return atlas.getSubimage(xCoordinate * 32, yCoordinate * 32, 32, 32);
    }
}
