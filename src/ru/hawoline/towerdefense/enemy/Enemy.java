package ru.hawoline.towerdefense.enemy;

import java.awt.*;

public class Enemy {
    private float x;
    private float y;
    private Rectangle bounds;
    private int health;
    private int id;
    private int enemyType;

    public Enemy(float x, float y, int id, int enemyType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.enemyType = enemyType;
        this.bounds = new Rectangle((int) x, (int) y, 32, 32);
    }

    public void move(float x, float y) {
        this.x += x;
        this.y += y;
    }
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getHealth() {
        return health;
    }

    public int getId() {
        return id;
    }

    public int getEnemyType() {
        return enemyType;
    }
}
