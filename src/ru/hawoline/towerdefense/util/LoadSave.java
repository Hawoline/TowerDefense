package ru.hawoline.towerdefense.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

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

    public static void createLevel(String name, int[] ids) {
        File textFile = new File("src/ru/hawoline/towerdefense/res/" + name);

        if (textFile.exists()) {
            return;
        } else {
            try {
                textFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        writeToFile(textFile, ids);
    }

    public static void writeToFile(File file, int[] ids) {
        try {
            PrintWriter printWriter = new PrintWriter(file);

            for (Integer id: ids) {
                printWriter.println(id);
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void readFromFile() {
        File txtFile = new File("src/ru/hawoline/towerdefense/res/testTestFile.txt");
        try {
            Scanner scanner = new Scanner(txtFile);
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static int[][] readLevel(String filename) {
        File txtFile = new File("src/ru/hawoline/towerdefense/res/" + filename);
        int[][] array = new int[20][20];
        try {
            Scanner scanner = new Scanner(txtFile);
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    if (scanner.hasNextInt()) {
                        array[i][j] = scanner.nextInt();
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return array;
    }

    public static void saveLevel(String name, int[][] level) {
        File file = new File("src/ru/hawoline/towerdefense/res/" + name);
        try {
            PrintWriter printWriter = new PrintWriter(file);
            for (int[] ints : level) {
                for (int levelId : ints) {
                    printWriter.println(levelId);
                }
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
