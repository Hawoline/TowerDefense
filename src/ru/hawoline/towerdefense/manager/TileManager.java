package ru.hawoline.towerdefense.manager;

import ru.hawoline.towerdefense.object.Tile;
import ru.hawoline.towerdefense.util.LoadSave;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileManager {
    public Tile grass;
    public Tile water;
    public Tile road;
    public BufferedImage atlas;
    public ArrayList<Tile> tiles = new ArrayList<>();

    public TileManager() {
        loadAtlas();
        createTiles();
    }

    private void createTiles() {
        tiles.add(grass = new Tile(getSprite(8, 1)));
        tiles.add(water = new Tile(getSprite(0, 6)));
        tiles.add(road = new Tile(getSprite(9, 0)));
    }

    private void loadAtlas() {
        atlas = LoadSave.getSpriteAtlas();
    }

    public BufferedImage getSprite(int id) {
        return tiles.get(id).getSprite();
    }
    public BufferedImage getSprite(int xCoordinate, int yCoordinate) {
        return atlas.getSubimage(xCoordinate * 32, yCoordinate * 32, 32, 32);
    }
}
