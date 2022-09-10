package com.streltsov.medium.task.utils;

import com.streltsov.medium.task.model.Person;

public class ValidationUtil {

    public static String checkArgs(String[] args) {
        if (args.length != 2) {
            System.out.println("Incorrect line of arguments!");
            System.exit(0);
        }
        return args[1];
    }

    public static boolean isCorrectOrderNumber(Integer listSize, String number) {
        return number.matches("\\b\\d+\\b") && listSize - 1 >= Integer.parseInt(number) - 1;
    }

    public static boolean isCorrectPhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("^\\+?(\\(\\w+\\)|\\w+[ -]\\(\\w{2,}\\)|\\w+)([ -]\\w{2,})*")) {
            return true;
        }
        System.out.println("Wrong number format!");
        return false;
    }

    public static boolean isPersonClass(Object record) {
        return record.getClass() == Person.class;
    }

    public static boolean isCorrectBirthDate(String date) {
        String dateRegex = "(19|20)\\d{2}(-|.)(0[1-9]|1[0-2])(-|.)([0-2]\\d|3[0-1])|" +
                "([0-2]\\d|3[0-1])[-/.][0-1][0-2][-/.](19|20)\\d{2}";
        return date.matches(dateRegex);
    }

    public static boolean isCorrectGender(String gender) {
        return gender.matches("[F|M]");
    }

    public static boolean isCorrectString(String name) {
        if (name.isEmpty() || name.equals(" ") || name.matches(" +")) {
            return false;
        }
        return true;
    }

}
