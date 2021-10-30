package ru.hawoline.towerdefense.ui;

import ru.hawoline.towerdefense.scene.Playing;

import java.util.ArrayList;

public class Bar {
    private int x;
    private int y;
    private int width;
    private int height;

    public Bar(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected int getX() {
        return x;
    }

    protected int getY() {
        return y;
    }

    protected int getWidth() {
        return width;
    }

    protected int getHeight() {
        return height;
    }
}
