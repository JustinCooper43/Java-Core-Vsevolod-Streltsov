package com.streltsov.hard.task.strategy;

import java.util.*;
import java.util.stream.Collectors;

public class AllModeStrategy extends Strategy {

    @Override
    protected List<String> finder(Map<Integer, String> mapData,
                                  Map<String, Set<Integer>> mapIndexInversion,
                                  List<String> listQueries) {

        Set<Integer> setIntegers = new HashSet<>();
        for (String listQuery : listQueries) {
            setIntegers.addAll(mapIndexInversion.getOrDefault(listQuery, new HashSet<>()));
        }
        if (setIntegers.size() == 0) {
            return new ArrayList<>();
        }
        return setIntegers.stream().map(mapData::get).collect(Collectors.toList());
    }
}

