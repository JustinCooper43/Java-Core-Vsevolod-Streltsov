package com.streltsov.hard.task;


import com.streltsov.hard.task.model.DocumentEntity;
import com.streltsov.hard.task.service.Menu;
import com.streltsov.hard.task.util.Utility;

public class Main {

    public static void main(String[] args) {
        final DocumentEntity documentEntity = Utility.parseDocument(args);
        Menu menu = new Menu(documentEntity);
        menu.mainMenu();
    }
}
