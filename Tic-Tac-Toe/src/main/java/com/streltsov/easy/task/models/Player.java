package com.streltsov.easy.task.models;

import com.streltsov.easy.task.strategy.StrategyMode;

import java.util.Scanner;

public class Player {

    private final char sign;
    private final StrategyMode modeOfPlayer;

    public Player(char sign, StrategyMode modeOfPlayer) {
        this.sign = sign;
        this.modeOfPlayer = modeOfPlayer;
    }


    public void makeStep(Board board, int step, Scanner scanner) {
        String nameStrategy = modeOfPlayer.getName();
        int[] coordinates;
        int x;
        int y;
        do {
            if (nameStrategy.equals("user")) {
                System.out.print("Enter the coordinates: ");
            } else {
                System.out.println("Making move level \"" + nameStrategy + "\"");
            }
            coordinates = modeOfPlayer.calculateMove(board, sign, step, scanner);
        } while (coordinates == null);
        x = coordinates[0];
        y = coordinates[1];
        board.setValueToCell(x, y, sign);
    }

    public char getSign() {
        return sign;
    }
}
