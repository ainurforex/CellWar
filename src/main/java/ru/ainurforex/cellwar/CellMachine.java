package ru.ainurforex.cellwar;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class CellMachine {
    private Canvas canvas;
    private static int width = 1000;
    private static int height = 1000;
    private static int z = 0;
    private static int teamRed=0;
    private static int teamBlue=0;
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
                    canvas.getGraphicsContext2D().getPixelWriter().setColor(i, j, Color.RED);
                }
                if (CellMachine.cells[i][j][z] == 2) {
                    canvas.getGraphicsContext2D().getPixelWriter().setColor(i, j, Color.BLUE);
                }
                if (CellMachine.cells[i][j][z] == 0) {
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
                int rand = (int) (Math.random() * 5);
                switch (rand) {
                    case (1):
                        CellMachine.cells[i][j][z] = 1;
                        break;
                    case (2):
                        CellMachine.cells[i][j][z] = 2;
                        break;
                    default:
                        CellMachine.cells[i][j][z] = 0;
                        break;
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
                int invasionKoef1 = 3;
                int invasionKoef2 = 3;

                int summType1 = summ8cellsType(i, j, 1);
                int summType2 = summ8cellsType(i, j, 2);

                if (cells[i][j][z] == 0 && summType1 == 3 && summType2 < invasionKoef1) {
                    cells[i][j][z1] = 1;
                }
                if (cells[i][j][z] == 0 && summType1 < invasionKoef2 && summType2 == 3) {
                    cells[i][j][z1] = 2;
                }


                if ((cells[i][j][z] == 1 && summType2 < invasionKoef1) && (summType1 == 2 || summType1 == 3)) {
                    cells[i][j][z1] = 1;
                }
                if ((cells[i][j][z] == 2 && summType1 < invasionKoef2) && (summType2 == 2 || summType2 == 3)) {
                    cells[i][j][z1] = 2;
                }

                if (cells[i][j][z] == 1 && (summType1 < 2 || summType1 > 3|| summType1+summType2 >3)) {
                    cells[i][j][z1] = 0;
                }
                if (cells[i][j][z] == 2  && (summType2 < 2 || summType2 > 3|| summType1+summType2 >3)) {
                    cells[i][j][z1] = 0;
                }
                /*if ((cells[i][j][z] == 1 && summ8cells(i, j) == 2)||summ8cells(i, j) == 3) {
                   cells[i][j][z1] = 1;
                 }
                */
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

        if (cells[x - 1][y - 1][z] > 0) {
            summ++;
        }
        if (cells[x][y - 1][z] > 0) {
            summ++;
        }
        if (cells[x + 1][y - 1][z] > 0) {
            summ++;
        }
        if (cells[x - 1][y][z] > 0) {
            summ++;
        }
        if (cells[x + 1][y][z] > 0) {
            summ++;
        }
        if (cells[x - 1][y + 1][z] > 0) {
            summ++;
        }
        if (cells[x][y + 1][z] > 0) {
            summ++;
        }
        if (cells[x + 1][y + 1][z] > 0) {
            summ++;
        }

        return summ;
          /*  summ = cells[x - 1][y - 1][z] + cells[x][y - 1][z] + cells[x + 1][y - 1][z] +
                    cells[x - 1][y][z] + cells[x + 1][y][z] +
                    cells[x - 1][y + 1][z] + cells[x][y + 1][z] + cells[x + 1][y + 1][z];
        */

    }

    public static int summ8cellsType(int x, int y, int type) {
        int summ = 0;
        if (cells[x - 1][y - 1][z] == type) {
            summ++;
        }
        if (cells[x][y - 1][z] == type) {
            summ++;
        }
        if (cells[x + 1][y - 1][z] == type) {
            summ++;
        }
        if (cells[x - 1][y][z] == type) {
            summ++;
        }
        if (cells[x + 1][y][z] == type) {
            summ++;
        }
        if (cells[x - 1][y + 1][z] == type) {
            summ++;
        }
        if (cells[x][y + 1][z] == type) {
            summ++;
        }
        if (cells[x + 1][y + 1][z] == type) {
            summ++;
        }

        return summ;

    }

    private static void clearArray() {
         teamBlue = 0;
         teamRed = 0;
        for (int i = 1; i < 1000 - 1; i++) {
            for (int j = 1; j < 1000 - 1; j++) {
                if (cells[i][j][z] == 1) {
                    teamRed++;
                }
                if (cells[i][j][z] == 2) {
                    teamBlue++;
                }

                cells[i][j][z] = 0;
            }
        }

    }

    public static int getTeamRed() {
        return teamRed;
    }

    public static int getTeamBlue() {
        return teamBlue;
    }
}