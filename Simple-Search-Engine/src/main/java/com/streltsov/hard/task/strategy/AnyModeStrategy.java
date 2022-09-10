package com.streltsov.hard.task.strategy;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class AnyModeStrategy extends Strategy {

    @Override
    protected List<String> finder(Map<Integer, String> mapData, Map<String, Set<Integer>> mapIndexInversion, List<String> listQueries) {

        final Set<Integer> setKeys = new TreeSet<>();
        for (String query : listQueries) {
            Set<Integer> integers = mapIndexInversion.get(query);
            if (integers == null) {
                integers = new TreeSet<>();
            }
            setKeys.addAll(integers);
        }
        return setKeys.stream().map(mapData::get).collect(Collectors.toList());
    }
}
