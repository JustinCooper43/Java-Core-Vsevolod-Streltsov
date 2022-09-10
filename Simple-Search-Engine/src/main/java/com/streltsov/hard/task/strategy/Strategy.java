package com.streltsov.hard.task.strategy;

import java.util.*;

public abstract class Strategy {

    public void findData(Map<Integer, String> mapData, Map<String, Set<Integer>> mapIndexInversion, String nameEmail) {

        List<String> listQueries = Arrays.asList(nameEmail.toLowerCase(Locale.ROOT).split(" +"));
        List<String> result = finder(mapData, mapIndexInversion, listQueries);

        if (result.isEmpty()) {
            System.out.println("No matching people found.");
        } else {
            result.forEach(System.out::println);
        }
    }

    protected abstract List<String> finder(Map<Integer, String> mapData,
                                           Map<String, Set<Integer>> mapIndexInversion,
                                           List<String> listQueries);

    public void printPeople(Collection<String> listData) {
        listData.forEach(System.out::println);
    }
}
















