package ru.hawoline.towerdefense.util;

import java.util.ArrayList;

public class Util {
    public static int[][] arrayListTo2dInt(ArrayList<Integer> arrayList, int ySize, int xSize) {
        int[][] array = new int[ySize][xSize];
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                int index = i * ySize + j;
                array[i][j] = arrayList.get(index);
            }
        }

        return array;
    }
}
