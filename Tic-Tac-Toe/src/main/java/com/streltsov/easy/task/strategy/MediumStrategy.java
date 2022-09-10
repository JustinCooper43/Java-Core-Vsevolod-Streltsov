package com.streltsov.easy.task.strategy;

import com.streltsov.easy.task.models.Board;

import java.util.Random;
import java.util.Scanner;

public class MediumStrategy implements StrategyMode {

    public final String name = "medium";

    @Override
    public int[] calculateMove(Board board, char signPlayer, int step, Scanner scanner) {

        char cell1;
        char cell2;
        char cell3;
        int coordMedX;
        int coordMedY;
        int[] bestCoords = new int[2];
        char[][] table = board.getTable();

        for (int i = 0; i < 3; i++) {
            char[] chars = table[i];

            cell1 = chars[0];
            cell2 = chars[1];
            cell3 = chars[2];

            if (checkOfEmptyCells(cell1, cell2, cell3)) {
                continue;
            }
            coordMedX = i;
            coordMedY = getCoordMedY(cell1, cell2, cell3);
            if (coordMedY < 3) {
                bestCoords[0] = coordMedX;
                bestCoords[1] = coordMedY;
                return bestCoords;
            }
        }

        for (int i = 0; i < 3; i++) {

            char[] arrChar = new char[3];

            for (int j = 0; j < table[i].length; j++) {
                arrChar[j] = table[j][i];
            }
            cell1 = arrChar[0];
            cell2 = arrChar[1];
            cell3 = arrChar[2];
            if (checkOfEmptyCells(cell1, cell2, cell3)) {
                continue;
            }
            coordMedX = getCoordMedY(cell1, cell2, cell3);
            coordMedY = i;
            if (coordMedX < 3) {
                bestCoords[0] = coordMedX;
                bestCoords[1] = coordMedY;
                return bestCoords;
            }
        }

        cell1 = table[0][0];
        cell2 = table[1][1];
        cell3 = table[2][2];
        if (checkOfEmptyCells(cell1, cell2, cell3)) {
            return randomMove(board);
        }

        coordMedY = getCoordMedY(cell1, cell2, cell3);

        if (coordMedY < 3) {
            bestCoords[0] = coordMedY;
            bestCoords[1] = coordMedY;
            return bestCoords;
        }

        cell1 = table[0][2];
        cell3 = table[2][0];

        if (checkOfEmptyCells(cell1, cell2, cell3)) {
            return randomMove(board);
        }

        coordMedY = getCoordMedY(cell1, cell2, cell3);
        if (coordMedY == 0) {
            coordMedX = 2;
        } else if (coordMedY == 1) {
            coordMedX = 1;
        } else if (coordMedY == 2) {
            coordMedX = 0;
        } else {
            return randomMove(board);
        }
        bestCoords[0] = coordMedX;
        bestCoords[1] = coordMedY;
        return bestCoords;
    }

    @Override
    public String getName() {
        return name;
    }

    private int getCoordMedY(char cell1, char cell2, char cell3) {
        if (cell2 == cell3 & cell1 == ' ') {
            return 0;
        } else if (cell1 == cell3 & cell2 == ' ') {
            return 1;
        } else if (cell1 == cell2 & cell3 == ' ') {
            return 2;
        } else {
            return 3;
        }
    }

    private boolean checkOfEmptyCells(char cell1, char cell2, char cell3) {
        return cell1 == ' ' & cell2 == ' ' & cell3 == ' ';
    }

    private int[] randomMove(Board board) {
        Random random = new Random();
        int x = random.nextInt(3);
        int y = random.nextInt(3);
        while (board.occupiedCellOrNot(x, y)) {
            x = random.nextInt(3);
            y = random.nextInt(3);
        }
        return new int[]{x, y};
    }
}
