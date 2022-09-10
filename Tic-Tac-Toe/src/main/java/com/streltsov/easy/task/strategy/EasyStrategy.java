package com.streltsov.easy.task.strategy;

import com.streltsov.easy.task.models.Board;

import java.util.Random;
import java.util.Scanner;

public class EasyStrategy implements StrategyMode {

    public final String name = "easy";

    @Override
    public int[] calculateMove(Board board, char signPlayer, int step, Scanner scanner) {

        Random random = new Random(100000);
        Random randomX = new Random(random.nextInt());
        Random randomY = new Random(random.nextInt());
        int randX;
        int randY;
        int[] result = new int[2];
        do {
            randX = randomX.nextInt(3);
            randY = randomY.nextInt(3);
        }
        while (board.occupiedCellOrNot(randX, randY));
        result[0] = randX;
        result[1] = randY;
        return result;
    }


    @Override
    public String getName() {
        return name;
    }
}
