package com.streltsov.easy.task.strategy;

import com.streltsov.easy.task.models.Board;

import java.util.Random;
import java.util.Scanner;

public class HardStrategy implements StrategyMode {

    public final String name = "hard";

    @Override
    public int[] calculateMove(Board board, char signPlayer, int step, Scanner scanner) {
        int[] bestCoords = {-1, -1};
        int bestScore = Integer.MIN_VALUE;
        int moveScore;

        if (step == 0) {
            Random random = new Random();
            return new int[]{random.nextInt(3), random.nextInt(3)};
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (board.getValueFromCell(i, j) == ' ') {

                    board.setValueToCell(i, j, signPlayer);
                    moveScore = minimax(board, signPlayer == 'O');
                    board.setValueToCell(i, j, ' ');

                    if (signPlayer == 'O') {
                        moveScore = -moveScore;
                    }

                    if (moveScore > bestScore) {
                        bestCoords[0] = i;
                        bestCoords[1] = j;
                        bestScore = moveScore;
                    }
                }
            }
        }
        return bestCoords;
    }


    private int minimax(Board board,boolean isMax) {

        if (board.isWin('X')) return 10;
        if (board.isWin('O')) return -10;
        if (board.checkingFullTable()) return 0;

        int best;
        if (isMax) {
            best = -1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.getValueFromCell(i, j) == ' ') {
                        board.setValueToCell(i, j, 'X');
                        best = Math.max(best, minimax(board, false));
                        board.setValueToCell(i, j, ' ');
                    }
                }
            }
        } else {
            best = 1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.getValueFromCell(i, j) == ' ') {
                        board.setValueToCell(i, j, 'O');
                        best = Math.min(best, minimax(board,  true));
                        board.setValueToCell(i, j, ' ');
                    }
                }
            }
        }
        return best;
    }


    @Override
    public String getName() {
        return name;
    }
}
