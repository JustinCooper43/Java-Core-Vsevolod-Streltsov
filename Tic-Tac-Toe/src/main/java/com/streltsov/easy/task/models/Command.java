package com.streltsov.easy.task.models;

public enum Command {

    EXIT("exit"),
    START("start"),
    ERROR("Bad parameters!");

    Command(String description) {
    }

    public static Command validInitialCommand(String inputCommand) {
        String[] splitCom = inputCommand.split(" +");
        if (splitCom[0].equals("exit")) {
            return Command.EXIT;
        }
        if (splitCom.length < 3) {
            return Command.ERROR;
        }
        return Command.START;
    }
}
