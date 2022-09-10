package com.streltsov.medium.task.utils;

import com.streltsov.medium.task.service.BookContacts;

import java.io.*;

public class SerializationUtils {

    private static String pathToFile;

    public static void writeObj(Object obj) throws IOException {
        File db = new File(pathToFile);
        db.createNewFile();
        try {
            FileOutputStream fos = new FileOutputStream(pathToFile);
            BufferedOutputStream bfo = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bfo);
            oos.writeObject(obj);
            oos.close();
            bfo.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BookContacts readObj() {
        File db = new File(pathToFile);
        BookContacts result = new BookContacts();
        FileInputStream fio = null;
        BufferedInputStream bfi = null;
        ObjectInputStream ois = null;
        try {
            if (!db.createNewFile()) {
                fio = new FileInputStream(pathToFile);
                bfi = new BufferedInputStream(fio);
                ois = new ObjectInputStream(bfi);
                result = (BookContacts) ois.readObject();
                ois.close();
                bfi.close();
                fio.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void setPathFile(String path) {
        SerializationUtils.pathToFile = path;
    }
}
