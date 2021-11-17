package ru.hawoline.towerdefense.ui;

import javax.swing.*;
import java.awt.*;

public class CustomButton {
    private String text;
    private int x;
    private int y;
    private int width;
    private int height;
    private int id;

    private boolean mouseOver;
    private boolean mousePressed;

    private Rectangle bounds;

    public CustomButton(String text, int x, int y, int width, int height) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = -1;

        initBounds();
    }

    public CustomButton(String text, int x, int y, int width, int height, int id) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;

        initBounds();
    }
    private void initBounds() {
        bounds = new Rectangle(x, y, width, height);
    }

    public void draw(Graphics graphics) {
        drawBody(graphics);
        drawText(graphics);
    }

    private void drawBody(Graphics graphics) {
        if (mouseOver) {
            graphics.setColor(Color.GRAY);
        } else {
            graphics.setColor(Color.WHITE);
        }
        graphics.fillRect(x, y, width, height);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(x, y, width, height);
    }

    private void drawText(Graphics graphics) {
        int textWidth = graphics.getFontMetrics().stringWidth(text);
        int textHeight = graphics.getFontMetrics().getHeight();

        graphics.drawString(text, x + width / 2 - textWidth / 2, y + height / 2 + textHeight / 2);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getId() {
        return id;
    }
}
