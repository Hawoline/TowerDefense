package ru.hawoline.towerdefense.manager;

import ru.hawoline.towerdefense.object.Tile;
import ru.hawoline.towerdefense.util.ImageFix;
import ru.hawoline.towerdefense.util.LoadSave;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileManager {
    public Tile grass;
    public Tile water;
    public Tile horizontalRoad;
    public Tile verticalRoad;

    public Tile bottomLeftRoadCorner;
    public Tile topLeftRoadCorner;
    public Tile topRightRoadCorner;
    public Tile bottomRightRoadCorner;

    public Tile bottomLeftWaterCorner;
    public Tile topLeftWaterCorner;
    public Tile topRightWaterCorner;
    public Tile bottomRightWaterCorner;

    public Tile leftBeach;
    public Tile topBeach;
    public Tile rightBeach;
    public Tile bottomBeach;

    public Tile topLeftIsland;
    public Tile topRightIsland;
    public Tile bottomRightIsland;
    public Tile bottomLeftIsland;

    public BufferedImage atlas;
    public ArrayList<Tile> tiles = new ArrayList<>();

    public ArrayList<Tile> straightRoads = new ArrayList<>();
    public ArrayList<Tile> roadCorners = new ArrayList<>();
    public ArrayList<Tile> waterCorners = new ArrayList<>();
    public ArrayList<Tile> beaches = new ArrayList<>();
    public ArrayList<Tile> islands = new ArrayList<>();

    public TileManager() {
        loadAtlas();
        createTiles();
    }

    private void createTiles() {
        int id = 0;

        tiles.add(grass = new Tile(getSprite(9, 0), id++, "Grass"));
        tiles.add(water = new Tile(getSprite(0, 0), id++, "Water"));

        id = createStraightRoadTiles(id);
        id = createRoadCornerTiles(id);
        id = createWaterCornerTiles(id);
        id = createBeachTiles(id);
        id = createIslandTiles(id);

        tiles.addAll(straightRoads);
        tiles.addAll(roadCorners);
        tiles.addAll(waterCorners);
        tiles.addAll(beaches);
        tiles.addAll(islands);
    }

    private int createStraightRoadTiles(int id) {
        BufferedImage straightRoadSprite = getSprite(8, 0);
        straightRoads.add(horizontalRoad = new Tile(straightRoadSprite, id++, "Road"));
        straightRoads.add(verticalRoad = new Tile(
                ImageFix.getRotateImage(straightRoadSprite, 90), id++, "VerticalRoad"));

        return id;
    }

    private int createRoadCornerTiles(int id) {
        BufferedImage roadCornerSprite = getSprite(7, 0);
        roadCorners.add(bottomLeftRoadCorner = new Tile(
                ImageFix.getRotateImage(roadCornerSprite, -90), id++, "bottomLeftRoadCorner"));
        roadCorners.add(topLeftRoadCorner = new Tile(roadCornerSprite, id++, "topLeftRoadCorner"));
        roadCorners.add(topRightRoadCorner = new Tile(
                ImageFix.getRotateImage(roadCornerSprite, 90), id++, "topRightRoadCorner"));
        roadCorners.add(bottomRightRoadCorner = new Tile(
                ImageFix.getRotateImage(roadCornerSprite, 180), id++, "bottomRightRoadCorner"));

        return id;
    }

    private int createWaterCornerTiles(int id) {
        BufferedImage[] waterCornerImages = getImages(0, 0, 5, 0);
        waterCorners.add(bottomLeftWaterCorner = new Tile(
                ImageFix.buildImage(waterCornerImages), id++, "BottomRightWaterCorner"));
        waterCorners.add(topLeftWaterCorner = new Tile(
                ImageFix.getBuildRotateImage(waterCornerImages, 90, 1), id++, "TopLeftWaterCorner"));
        waterCorners.add(topRightWaterCorner = new Tile(
                ImageFix.getBuildRotateImage(waterCornerImages, 180, 1), id++, "TopRightWaterCorner"));
        waterCorners.add(bottomRightWaterCorner = new Tile(
                ImageFix.getBuildRotateImage(waterCornerImages, -90, 1), id++, "BottomRightWaterCorner"));

        return id;
    }

    private int createBeachTiles(int id) {
        BufferedImage[] beachSprites = getImages(0,0,6, 0);
        beaches.add(leftBeach = new Tile(
                ImageFix.getBuildRotateImage(beachSprites, -90, 1), id++, "LeftBeach"));
        beaches.add(topBeach = new Tile(
                ImageFix.buildImage(beachSprites), id++, "TopBeach"));
        beaches.add(rightBeach = new Tile(
                ImageFix.getBuildRotateImage(beachSprites, 90, 1), id++, "RightBeach"));
        beaches.add(bottomBeach = new Tile(
                ImageFix.getBuildRotateImage(beachSprites, 180, 1), id++, "BottomBeach"));

        return id;
    }

    private int createIslandTiles(int id) {
        BufferedImage[] islandSprites = getImages(0,0,4, 0);
        islands.add(topLeftIsland = new Tile(
                ImageFix.buildImage(islandSprites), id++, "topLeftIsland"));
        islands.add(topRightIsland = new Tile(
                ImageFix.getBuildRotateImage(islandSprites, 90, 1), id++, "topRightIsland"));
        islands.add(bottomRightIsland = new Tile(
                ImageFix.getBuildRotateImage(islandSprites, 180, 1), id++, "bottomRightIsland"));
        islands.add(bottomLeftIsland = new Tile(
                ImageFix.getBuildRotateImage(islandSprites, -90, 1), id++, "bottomLeftIsland"));

        return id;
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

    public ArrayList<Tile> getStraightRoads() {
        return straightRoads;
    }

    public ArrayList<Tile> getRoadCorners() {
        return roadCorners;
    }

    public ArrayList<Tile> getWaterCorners() {
        return waterCorners;
    }

    public ArrayList<Tile> getBeaches() {
        return beaches;
    }

    public ArrayList<Tile> getIslands() {
        return islands;
    }
}
