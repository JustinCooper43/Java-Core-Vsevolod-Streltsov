package com.streltsov.hard.task.model;

import java.util.Map;
import java.util.Set;

public class DocumentEntity {

    Map<Integer, String> mapData;
    Map<String, Set<Integer>> mapIndexInversion;

    public Map<Integer, String> getMapData() {
        return mapData;
    }

    public void setMapData(Map<Integer, String> mapData) {
        this.mapData = mapData;
    }

    public Map<String, Set<Integer>> getMapIndexInversion() {
        return mapIndexInversion;
    }

    public void setMapIndexInversion(Map<String, Set<Integer>> mapIndexInversion) {
        this.mapIndexInversion = mapIndexInversion;
    }
}
