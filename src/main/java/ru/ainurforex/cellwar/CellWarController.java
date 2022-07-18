package ru.ainurforex.cellwar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.Canvas;

public class CellWarController {
    @FXML
    public TextField teamRed;
    @FXML
    public TextField teamBlue;
    private static int mouseX;
    private static int mouseY;
    public Canvas canvas;


    public void updateMouse(MouseEvent e) {
        CellMachine draw = new CellMachine(canvas);
        mouseX = (int) e.getSceneX();
        mouseY = (int) e.getSceneY();
        draw.drawCellMouse(mouseX, mouseY);
    }


    private void stepArray() {
        CellMachine draw = new CellMachine(canvas);
        CellMachine.cellsCalculate();
        teamBlue.setText(CellMachine.getTeamBlue() + "");
        teamRed.setText(CellMachine.getTeamRed() + "");
        draw.drawArray();

    }

    private void stepUntil() {
        for (int i = 0; i < 100; i++) {
            CellMachine.cellsCalculate();
        }
        stepArray();
    }

    @FXML
    private void buttonStep1(ActionEvent actionEvent) {
        stepArray();
    }

    @FXML
    private void buttonStep100(ActionEvent actionEvent) {

        stepUntil();
    }

    @FXML
    private void randomCells(ActionEvent actionEvent) {
        CellMachine draw = new CellMachine(canvas);
        draw.randomCells();
        draw.drawArray();
        teamBlue.setText(CellMachine.getTeamBlue() + "");
        teamRed.setText(CellMachine.getTeamRed() + "");
    }

    @FXML
    private void add1000Red(ActionEvent actionEvent) {
        CellMachine draw = new CellMachine(canvas);
        draw.putRandomTypeCells(1000, 1);
        draw.drawArray();
        teamRed.setText(CellMachine.getTeamRed() + "");
    }

    @FXML
    private void add1000Blue(ActionEvent actionEvent) {
        CellMachine draw = new CellMachine(canvas);
        draw.putRandomTypeCells(1000, 2);
        draw.drawArray();
        teamBlue.setText(CellMachine.getTeamBlue() + "");
    }


}