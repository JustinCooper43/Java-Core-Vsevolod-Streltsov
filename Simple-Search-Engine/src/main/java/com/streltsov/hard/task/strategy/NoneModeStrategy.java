package com.streltsov.hard.task.strategy;

import java.util.*;

public class NoneModeStrategy extends Strategy {

    @Override
    protected List<String> finder(Map<Integer, String> mapData, Map<String, Set<Integer>> mapIndexInversion, List<String> listQueries) {

        final Set<Integer> blackList = new TreeSet<>();
        for (String query : listQueries) {
            blackList.addAll(mapIndexInversion.get(query));
        }
        List<String> result = new ArrayList<>();
        for (Integer integer : mapData.keySet()) {
            if (!blackList.contains(integer)) {
                result.add(mapData.get(integer));
            }
        }
        return result;
    }
}
