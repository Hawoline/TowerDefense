package ru.hawoline.towerdefense.scene;

import java.awt.*;

public interface SceneMethod {
    void render(Graphics graphics);

    void mouseClicked(int x, int y);

    void mouseMoved(int x, int y);

    void mousePressed(int x, int y);

    void mouseReleased(int x, int y);

    void mouseDragged(int x, int y);
}
