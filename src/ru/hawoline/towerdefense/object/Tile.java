package ru.hawoline.towerdefense.object;

import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage[] sprite;
    private int id;
    private String name;

    public Tile(BufferedImage sprite, int id, String name) {
        this.sprite = new BufferedImage[1];
        this.sprite[0] = sprite;
        this.id = id;
        this.name = name;
    }

    public Tile(BufferedImage[] sprite, int id, String name) {
        this.sprite = sprite;
        this.id = id;
        this.name = name;
    }

    public BufferedImage getSprite() {
        return sprite[0];
    }

    public BufferedImage getSprite(int animationIndex) {
        if (animationIndex > sprite.length - 1) {
            return sprite[sprite.length - 1];
        }
        return sprite[animationIndex];
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isAnimation() {
        return sprite.length > 1;
    }
}
