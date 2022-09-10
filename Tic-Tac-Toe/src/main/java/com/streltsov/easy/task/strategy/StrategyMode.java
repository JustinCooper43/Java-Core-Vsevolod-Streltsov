package com.streltsov.easy.task.strategy;

import com.streltsov.easy.task.models.Board;

import java.util.Scanner;

public interface StrategyMode {

    int[] calculateMove(Board board, char signPlayer, int step, Scanner scanner);

    String getName();
}
