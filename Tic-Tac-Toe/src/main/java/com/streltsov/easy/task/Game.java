package com.streltsov.easy.task;

import com.streltsov.easy.task.models.Command;
import com.streltsov.easy.task.models.Play;
import com.streltsov.easy.task.models.Player;
import com.streltsov.easy.task.strategy.*;
import com.streltsov.easy.task.strategy.Process;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Game {

    private Play play;

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String inputCommand;
        do {
            System.out.println("Input command:");
            inputCommand = scanner.nextLine();
            final Command command = Command.validInitialCommand(inputCommand);
            if (command.equals(Command.EXIT)) {
                break;
            } else if (command.equals(Command.ERROR)) {
                System.out.println("Bad parameters!");
            } else if (peakMode(inputCommand)) {
                Process process = new Process(play, scanner);
                process.execute();
            }
        } while (!inputCommand.equals("exit"));
    }

    boolean peakMode(String inputCommand) {
        String[] splitCom = inputCommand.toLowerCase(Locale.ROOT).split(" +");
        final Map<String, Play> dbModes = getDBModes();
        String com1 = splitCom[0];
        String key = splitCom[1] + " " + splitCom[2];

        if (splitCom.length == 3 && dbModes.containsKey(key) && com1.equals("start")) {
            play = dbModes.get(key);
            return true;
        } else {
            System.out.println("Incorrect command");
            return false;
        }
    }

    public static Map<String, Play> getDBModes() {
        Map<String, Play> map = new HashMap<>();
        map.put("user user", new Play(new Player('X', new UserStrategy()),
                new Player('O', new UserStrategy())));
        map.put("user easy", new Play(new Player('X', new UserStrategy()),
                new Player('O', new EasyStrategy())));
        map.put("easy user", new Play(new Player('X', new EasyStrategy()),
                new Player('O', new UserStrategy())));
        map.put("easy easy", new Play(new Player('X', new EasyStrategy()),
                new Player('O', new EasyStrategy())));
        map.put("medium user", new Play(new Player('X', new MediumStrategy()),
                new Player('O', new UserStrategy())));
        map.put("user medium", new Play(new Player('X', new UserStrategy()),
                new Player('O', new MediumStrategy())));
        map.put("medium medium", new Play(new Player('X', new MediumStrategy()),
                new Player('O', new MediumStrategy())));
        map.put("hard user", new Play(new Player('X', new HardStrategy()),
                new Player('O', new UserStrategy())));
        map.put("user hard", new Play(new Player('X', new UserStrategy()),
                new Player('O', new HardStrategy())));
        map.put("hard hard", new Play(new Player('X', new HardStrategy()),
                new Player('O', new HardStrategy())));
        return map;
    }

}
