package ru.hawoline.towerdefense.util;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageFix {
    // Rotate

    public static BufferedImage getRotateImage(BufferedImage image, int rotateAngle) {
        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage newImage = new BufferedImage(width, height, image.getType());
        Graphics2D graphics2D = newImage.createGraphics();

        graphics2D.rotate(Math.toRadians(rotateAngle), width / 2, height / 2);
        graphics2D.drawImage(image, 0, 0, null);
        graphics2D.dispose();

        return newImage;
    }

    // Image layer build

    public static BufferedImage buildImage(BufferedImage[] images) {
        int width = images[0].getWidth();
        int height = images[0].getHeight();

        BufferedImage newImage = new BufferedImage(width, height, images[0].getType());
        Graphics2D graphics2D = newImage.createGraphics();

        for (BufferedImage image: images) {
            graphics2D.drawImage(image, 0, 0, null);
        }
        graphics2D.dispose();

        return newImage;
    }

    public static BufferedImage getBuildRotateImage(BufferedImage[] images, int rotateAngle, int rotateAtIndex) {
        int width = images[0].getWidth();
        int height = images[0].getHeight();

        BufferedImage newImage = new BufferedImage(width, height, images[0].getType());
        Graphics2D graphics2D = newImage.createGraphics();

        for (int i = 0; i < images.length; i++) {
            if (rotateAtIndex == i) {
                graphics2D.rotate(Math.toRadians(rotateAngle), width / 2, height / 2);
            }
            graphics2D.drawImage(images[i], 0, 0, null);
            if (rotateAtIndex == i) {
                graphics2D.rotate(Math.toRadians(-rotateAngle), width / 2, height / 2);
            }
        }
        graphics2D.dispose();

        return newImage;
    }
}
