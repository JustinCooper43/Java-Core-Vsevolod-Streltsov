package com.streltsov.easy.task.models;

import java.util.Arrays;

public class Board {

    private final char[][] table = getInitialTable();

    public static char[][] getInitialTable() {
        char[][] arrChar = new char[3][3];
        for (char[] chars : arrChar) {
            Arrays.fill(chars, ' ');
        }
        return arrChar;
    }

    public void setValueToCell(int x, int y, char charToCell) {
        table[x][y] = charToCell;
    }

    public char getValueFromCell(int x, int y) {
        return table[x][y];
    }

    public boolean occupiedCellOrNot(int coordX, int coordY) {
        return table[coordX][coordY] == 'X' || table[coordX][coordY] == 'O';
    }

    public void showTable() {
        System.out.println("---------");
        for (char[] chars : table) {
            System.out.print("|");
            for (int i = 0; i < chars.length; i++) {
                char aChar = chars[i];
                if (aChar != 'X' & aChar != 'O') {
                    aChar = ' ';
                }
                if (i == 0) {
                    System.out.print(" " + aChar + " ");
                } else {
                    System.out.print(aChar + " ");
                }
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");
    }



    public boolean checkingFullTable() {
        for (char[] chars : table) {
            for (char aChar : chars) {
                if (aChar != 'X' & aChar != 'O') {
                    return false;
                }
            }
        }
        return true;
    }

    public void status(char inputCharToCell) {
        if (isWin(inputCharToCell)) {
            System.out.println(inputCharToCell + " wins");
        } else if (checkingFullTable()) {
            System.out.println("Draw");
        }
    }

    public char[][] getTable() {
        return table;
    }

    public boolean isWin(char inputCharToCell) {
        return (table[0][0] == inputCharToCell && table[0][1] == inputCharToCell && table[0][2] == inputCharToCell) ||
                (table[1][0] == inputCharToCell && table[1][1] == inputCharToCell && table[1][2] == inputCharToCell) ||
                (table[2][0] == inputCharToCell && table[2][1] == inputCharToCell && table[2][2] == inputCharToCell) ||

                (table[0][0] == inputCharToCell && table[1][0] == inputCharToCell && table[2][0] == inputCharToCell) ||
                (table[0][1] == inputCharToCell && table[1][1] == inputCharToCell && table[2][1] == inputCharToCell) ||
                (table[0][2] == inputCharToCell && table[1][2] == inputCharToCell && table[2][2] == inputCharToCell) ||

                (table[0][0] == inputCharToCell && table[1][1] == inputCharToCell && table[2][2] == inputCharToCell) ||
                (table[0][2] == inputCharToCell && table[1][1] == inputCharToCell && table[2][0] == inputCharToCell);
    }

}
