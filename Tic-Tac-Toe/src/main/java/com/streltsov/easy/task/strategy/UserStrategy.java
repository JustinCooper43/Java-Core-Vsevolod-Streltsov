package com.streltsov.easy.task.strategy;

import com.streltsov.easy.task.models.Board;

import java.util.Scanner;

public class UserStrategy implements StrategyMode {

    private static final int ASCII_VALUE_0 = 48;
    private static final int ASCII_VALUE_9 = 57;

    public final String name = "user";

    @Override
    public int[] calculateMove(Board board, char signPlayer, int step, Scanner scanner) {
        String command = scanner.nextLine();

        if (!validationString(command)) {
            int[] coordinates = extractIntCoordinate(command);
            final int x = coordinates[0];
            final int y = coordinates[1];
            if (board.occupiedCellOrNot(x, y)) {
                System.out.println("This cell is occupied! Choose another one!");
                board.showTable();
                return null;
            } else {
                return coordinates;
            }
        }
    }

    private static int[] extractIntCoordinate(String strInput) {
        String strClean = strInput.replaceAll(" ", "");

        return new int[]{Integer.parseInt(String.valueOf(strClean.charAt(0))) - 1,
                Integer.parseInt(String.valueOf(strClean.charAt(1))) - 1};
    }

    private static boolean validationString(String strInput) {
        String cleanStr = strInput.replaceAll(" ", "");
        for (char var : cleanStr.toCharArray()) {
            if ((int) var < ASCII_VALUE_0 | (int) var > ASCII_VALUE_9) {
                System.out.println("You should enter numbers!");
                return true;
            }
        }

        for (String var : strInput.split(" ")) {
            int num = Integer.parseInt(var);
            if (num > 3 || num < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
                return true;
            }
        }
        return false;
    }

    @Override
    public String getName() {
        return name;
    }
}
