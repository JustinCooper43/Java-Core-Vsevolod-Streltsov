package com.streltsov.hard.task.util;

import com.streltsov.hard.task.model.DocumentEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Utility {

    public static DocumentEntity parseDocument(String[] args) {
        String fileName = "";
        String pathFolder = "src/main/resources/";
        if (args.length > 1) {
            fileName = args[1];
        } else {
            System.out.println("Empty line of arguments!");
        }

        File file = new File(pathFolder + fileName);
        DocumentEntity entity = new DocumentEntity();

        Map<Integer, String> mapData = new HashMap<>();
        Map<String, Set<Integer>> mapIndexInversion = new HashMap<>();

        try (Scanner scanner = new Scanner(file)) {
            int indexKey = 0;
            while (scanner.hasNext()) {
                String lineData = scanner.nextLine();
                mapData.put(indexKey, lineData);

                String[] splitValue = lineData.split("\\s");
                for (String word : splitValue) {
                    String keyStr = word.toLowerCase(Locale.ROOT);
                    mapIndexInversion.putIfAbsent(keyStr, new TreeSet<>());
                    if (mapIndexInversion.containsKey(keyStr)) {
                        mapIndexInversion.get(keyStr).add(indexKey);
                    }
                }
                indexKey++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File is not found");
            return entity;
        }
        entity.setMapData(mapData);
        entity.setMapIndexInversion(mapIndexInversion);
        return entity;
    }
}
