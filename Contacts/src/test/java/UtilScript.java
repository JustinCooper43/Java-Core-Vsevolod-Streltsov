public class UtilScript {

    static final String FILL_BOOK_SCRIPT = "add\n" +
            "person\n" +
            "Bob\n" +
            "Dylan\n" +
            "1995-04-05\n" +
            "M\n" +
            "+0 (123) 456-789-1123\n" +
            "add\n" +
            "person\n" +
            "Anna\n" +
            "Smith\n" +
            "1993-24-05\n" +
            "F\n" +
            "+0 (123) 456-234-4326\n" +
            "add\n" +
            "organization\n" +
            "Pizza Shop 1\n" +
            "Wall St. 1\n" +
            "+0 (123) 456-789-9999\n" +
            "add\n" +
            "organization\n" +
            "Pizza Hut 2\n" +
            "Sun St. 2\n" +
            "+1 (351) 654-987-9999\n";

    static final String COUNT_SCRIPT = "count\n" +
            "exit\n";

    static final String SEARCH_SCRIPT = "search\n" +
            "Dylan\n" +
            "again\n" +
            "Pizza\n" +
            "back\n" +
            "exit\n";

    static final String LIST_SCRIPT = "list\n" +
            "exit\n";

    static final String ADD_ORGANIZATION_SCRIPT = "add\n" +
            "organization\n" +
            "Pizza Shop\n" +
            "Wall St. 1\n" +
            "+0 (123) 456-789-9999\n" +
            "list\n" +
            "back\n" +
            "exit";

    static final String ADD_PERSON_SCRIPT = "add\n" +
            "person\n" +
            "John\n" +
            "Smith\n" +
            "1995-04-05\n" +
            "M\n" +
            "+0 (123) 456-789-ABcd\n" +
            "list\n" +
            "back\n" +
            "exit";

    static final String EDIT_SCRIPT_ORG = "search\n" +
            "Pizza\n" +
            "1\n" +
            "edit\n" +
            "name\n" +
            "Car Shop\n" +
            "edit\n" +
            "address\n" +
            "Car Str.3\n" +
            "edit\n" +
            "number\n" +
            "+1 (433) 156-789-9009\n" +
            "menu\n" +
            "search\n" +
            "Car Shop\n" +
            "back\n" +
            "exit\n";

    static final String EDIT_SCRIPT_PER = "search\n" +
            "Dylan\n" +
            "1\n" +
            "edit\n" +
            "name\n" +
            "Gwen\n" +
            "edit\n" +
            "surname\n" +
            "Stacy\n" +
            "edit\n" +
            "birth\n" +
            "2017-01-01\n" +
            "edit\n" +
            "gender\n" +
            "F\n" +
            "menu\n" +
            "search\n" +
            "Gwen\n" +
            "back\n" +
            "exit\n";

    static final String DELETE_SCRIPT = "search\n" +
            "Bob\n" +
            "1\n" +
            "delete\n" +
            "search\n" +
            "Bob\n" +
            "back\n" +
            "exit\n";

    static final String BAD_DOB_SCRIPT = "add\n" +
            "person\n" +
            "Name\n" +
            "Surname\n" +
            "birthdate\n" +
            "M\n" +
            "+1 (433) 156-789-9009\n" +
            "search\n" +
            "Name\n" +
            "1\n" +
            "menu\n" +
            "exit\n";

    static final String BAD_GENDER_SCRIPT = "add\n" +
            "person\n" +
            "Name\n" +
            "Surname\n" +
            "1992-02-23\n" +
            "Bad gender\n" +
            "+1 (433) 156-789-9009\n" +
            "search\n" +
            "Name\n" +
            "1\n" +
            "menu\n" +
            "exit\n";

    static final String BAD_PHONE_SCRIPT = "add\n" +
            "person\n" +
            "Name\n" +
            "Surname\n" +
            "1992-02-23\n" +
            "Bad gender\n" +
            "!!!!!!!!\n" +
            "search\n" +
            "Name\n" +
            "1\n" +
            "menu\n" +
            "exit\n";

    static final String EMPTY_NAME_SCRIPT = "add\n" +
            "person\n" +
            "\n" +
            "Surname\n" +
            "1992-02-23\n" +
            "Bad gender\n" +
            "+1 (433) 156-789-9009\n" +
            "search\n" +
            "[no data]\n" +
            "1\n" +
            "menu\n" +
            "exit\n";

    static final String EMPTY_SURNAME_SCRIPT = "add\n" +
            "person\n" +
            "Name\n" +
            "\n" +
            "1992-02-23\n" +
            "Bad gender\n" +
            "+1 (433) 156-789-9009\n" +
            "search\n" +
            "Name\n" +
            "1\n" +
            "menu\n" +
            "exit\n";

    static final String EMPTY_ADDRESS_SCRIPT = "add\n" +
            "organization\n" +
            "Pizza Shop\n" +
            "\n" +
            "+0 (123) 456-789-9999\n" +
            "search\n" +
            "Pizza Shop\n" +
            "1\n" +
            "menu\n" +
            "exit\n";

    static final String UPDATE_PERSON_SCRIPT = "search\n" +
            "Dylan\n" +
            "1\n" +
            "edit\n" +
            "name\n" +
            "Gwen\n" +
            "edit\n" +
            "surname\n" +
            "Stacy\n" +
            "edit\n" +
            "birth\n" +
            "2017-01-01\n" +
            "edit\n" +
            "gender\n" +
            "F\n" +
            "menu\n" +
            "search\n" +
            "Gwen\n" +
            "1\n" +
            "back\n" +
            "exit\n";
}
