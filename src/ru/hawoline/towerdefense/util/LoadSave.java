package ru.hawoline.towerdefense.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {
    public static BufferedImage getSpriteAtlas() {
        InputStream inputStream = LoadSave.class.getClassLoader().getResourceAsStream("ru/hawoline/towerdefense/res/spriteatlas.png");

        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bufferedImage;
    }
}
