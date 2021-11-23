package ru.hawoline.towerdefense.manager;

import ru.hawoline.towerdefense.enemy.Enemy;
import ru.hawoline.towerdefense.scene.Playing;
import ru.hawoline.towerdefense.util.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class EnemyManager {
    private Playing playing;
    private BufferedImage[] enemyImages;
    private ArrayList<Enemy> enemies = new ArrayList<>();

    public EnemyManager(Playing playing) {
        this.playing = playing;
        enemyImages = new BufferedImage[4];
        this.enemies.add(new Enemy(32 * 3, 32 * 9, 0, 0));
        loadEnemyImages();
    }

    private void loadEnemyImages() {
        BufferedImage spriteAtlas = LoadSave.getSpriteAtlas();
        for (int i = 0; i < enemyImages.length; i++) {
            enemyImages[i] = spriteAtlas.getSubimage(i * 32, 32, 32, 32);
        }
    }

    public void update() {
        for (Enemy enemy : enemies) {
            if (enemy.getX() > 640 || enemy.getY() > 640) {
                continue;
            }
            enemy.move(0.5f, 0);
        }
    }

    public void addEnemy(int x, int y) {
        enemies.add(new Enemy(x, y, 1, 0));
    }

    public void draw(Graphics graphics) {
        for (Enemy enemy: enemies) {
            drawEnemy(enemy, graphics);
        }
    }

    private void drawEnemy(Enemy enemy, Graphics graphics) {
        graphics.drawImage(enemyImages[0], (int) enemy.getX(), (int) enemy.getY(), null);
    }
}

