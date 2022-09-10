package com.streltsov.easy.task.strategy;

import com.streltsov.easy.task.models.Board;
import com.streltsov.easy.task.models.Play;
import com.streltsov.easy.task.models.Player;

import java.util.Scanner;

public class Process {

    Board board = new Board();
    Player player1;
    Player player2;
    Scanner scanner;

    public Process(Play play, Scanner scanner) {
        this.player1 = play.getPlayer1();
        this.player2 = play.getPlayer2();
        this.scanner = scanner;
    }

    public void execute() {
        board.showTable();
        final char signPlayer1 = player1.getSign();
        final char signPlayer2 = player2.getSign();
        int step = 0;

        while (!(board.checkingFullTable() || board.isWin(signPlayer1) || board.isWin(signPlayer2))) {
            player1.makeStep(board, step, scanner);
            step += 1;
            board.showTable();
            board.status(signPlayer1);
            if (board.checkingFullTable() || board.isWin(signPlayer1) || board.isWin(signPlayer2)) {
                return;
            }

            player2.makeStep(board, step,scanner);
            step += 1;
            board.showTable();
            board.status(signPlayer2);

            if (board.checkingFullTable()) {
                return;
            }
        }
    }

}

