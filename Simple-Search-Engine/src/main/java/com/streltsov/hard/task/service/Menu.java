package com.streltsov.hard.task.service;

import com.streltsov.hard.task.model.DocumentEntity;
import com.streltsov.hard.task.strategy.AllModeStrategy;
import com.streltsov.hard.task.strategy.AnyModeStrategy;
import com.streltsov.hard.task.strategy.NoneModeStrategy;
import com.streltsov.hard.task.strategy.Strategy;

import java.util.*;

public class Menu {

    Map<Integer, String> mapData;
    Map<String, Set<Integer>> mapIndexInversion;

    public Menu(DocumentEntity entity) {
        this.mapData = entity.getMapData();
        this.mapIndexInversion = entity.getMapIndexInversion();
    }

    public void mainMenu() {
        final Collection<String> listData = mapData.values();
        Scanner scannerCommand = new Scanner(System.in);

        showMenu();
        String numberCom;
        while (true) {

            numberCom = scannerCommand.nextLine();

            System.out.println();
            if (numberCom.equals("0")) {
                System.out.println("Bye!");
                break;
            }
            Strategy strategy = new AnyModeStrategy();
            switch (numberCom) {
                case "1":
                    System.out.println("Select a matching strategy: ALL, ANY, NONE");
                    strategy = getStrategy(scannerCommand.nextLine());
                    System.out.println("Enter a name or email to search all suitable people.");
                    String nameEmail = scannerCommand.nextLine();
                    strategy.findData(mapData, mapIndexInversion, nameEmail);
                    System.out.println();
                    break;
                case "2":
                    System.out.println("=== List of people ===");
                    strategy.printPeople(listData);
                    System.out.println();
                    break;
                default:
                    System.out.println("Incorrect option! Try again.");
                    System.out.println();
                    break;
            }
            showMenu();
        }
        scannerCommand.close();
    }

    private static void showMenu() {
        System.out.println("=== Menu ===\n" +
                "1. Find a person\n" +
                "2. Print all people\n" +
                "0. Exit");
    }

    public static Strategy getStrategy(String strategyCommand) {
        String com = strategyCommand.toUpperCase(Locale.ROOT);
        switch (com) {
            case "ANY":
                return new AnyModeStrategy();
            case "ALL":
                return new AllModeStrategy();
            case "NONE":
                return new NoneModeStrategy();
        }
        return new AnyModeStrategy();
    }
}
