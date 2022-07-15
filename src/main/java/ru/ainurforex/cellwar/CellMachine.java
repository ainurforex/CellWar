package ru.ainurforex.cellwar;

import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class CellMachine {
    private Canvas canvas;
    private static int width = 1000;
    private static int height = 1000;
    private static int z = 0;
    public static int[][][] cells = new int[width][height][2];


    CellMachine(Canvas canvas) {
        this.canvas = canvas;
        this.width = (int) canvas.getWidth();
        this.height = (int) canvas.getHeight();
    }

    public void drawArray() {

        for (int i = 1; i < 1000 - 1; i++) {
            for (int j = 1; j < 1000 - 1; j++) {
                if (CellMachine.cells[i][j][z] == 1) {
                    canvas.getGraphicsContext2D().getPixelWriter().setColor(i, j, Color.CORAL);
                } else {
                    canvas.getGraphicsContext2D().getPixelWriter().setColor(i, j, Color.WHITE);
                }
            }
        }


    }

    public void drawCellMouse(int mouseX, int mouseY) {
        canvas.getGraphicsContext2D().getPixelWriter().setColor(mouseX - 15, mouseY - 15, Color.CORAL);
        CellMachine.arrayWright(mouseX - 15, mouseY - 15);

    }


    public static void arrayWright(int x, int y) {
        cells[x][y][z] = 1;
    }

    public void randomCells() {
        for (int i = 1; i < 1000 - 1; i++) {
            for (int j = 1; j < 1000 - 1; j++) {
                if ((int) (Math.random() * 20) == 1) {
                    CellMachine.cells[i][j][z] = 1;
                } else {
                    CellMachine.cells[i][j][z] = 0;
                }
            }
        }

    }

    public static void cellsCalculate() {
        int z1 = 0;
        if (z == 0) {
            z1 = 1;
        } else {
            z1 = 0;
        }

        for (int i = 1; i < 1000 - 1; i++) {
            for (int j = 1; j < 1000 - 1; j++) {
                if (cells[i][j][z] == 0 && summ8cells(i, j) == 3) {
                    cells[i][j][z1] = 1;
                }
                if (cells[i][j][z] == 1 && summ8cells(i, j) < 2|| summ8cells(i, j) > 3) {
                    cells[i][j][z1] = 0;
                }

               if (cells[i][j][z] == 1 && summ8cells(i, j) == 2||summ8cells(i, j) == 3) {
                    cells[i][j][z1] = 1;
                }

            }


        }
        clearArray();
        if (z == 0) {
            z = 1;
        } else {
            z = 0;
        }
    }

    public static int summ8cells(int x, int y) {
        int summ = 0;

        summ = cells[x - 1][y - 1][z] + cells[x][y - 1][z] + cells[x + 1][y - 1][z] +
                cells[x - 1][y][z] + cells[x + 1][y][z] +
                cells[x - 1][y + 1][z] + cells[x][y + 1][z] + cells[x + 1][y + 1][z];

        return summ;

    }

    private static void clearArray() {
        for (int i = 1; i < 1000 - 1; i++) {
            for (int j = 1; j < 1000 - 1; j++) {
                cells[i][j][z] = 0;
            }
        }
    }


}