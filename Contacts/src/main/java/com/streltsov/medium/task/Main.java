package com.streltsov.medium.task;

import com.streltsov.medium.task.service.BookContacts;
import com.streltsov.medium.task.utils.SerializationUtils;
import com.streltsov.medium.task.utils.ValidationUtil;
import com.streltsov.medium.task.view.Menu;

import java.io.IOException;
import java.util.Optional;

public class Main {

    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir") + "/Contacts/src/main/resources/";
        String fileName = ValidationUtil.checkArgs(args);

        SerializationUtils.setPathFile(path + fileName);
        final BookContacts bookContactsDB = Optional.ofNullable(SerializationUtils.readObj()).orElse(new BookContacts());
        Menu menu = new Menu();
        menu.menuActions(bookContactsDB);
    }
}


