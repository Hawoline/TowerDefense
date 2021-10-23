package ru.hawoline.towerdefense;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends JPanel {
    private Random random;
    private Game game;
    private Dimension size;
    private Render render;

    public GameScreen(Game game) {
        this.game = game;
        random = new Random();
        setPanelSize();
        render = new Render(this);
    }

    private void setPanelSize() {
        size = new Dimension(640, 640);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }


    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

    }
}
