package com.streltsov.medium.task.view;

import com.streltsov.medium.task.model.Organization;
import com.streltsov.medium.task.model.Person;
import com.streltsov.medium.task.model.SearchResult;
import com.streltsov.medium.task.service.BookContacts;
import com.streltsov.medium.task.utils.SerializationUtils;
import com.streltsov.medium.task.utils.ValidationUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Menu {

    public void menuActions(BookContacts bookContacts) throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = true;
        do {
            System.out.println("[menu] Enter action (add, list, search, count, exit):");
            String actionCommand = scanner.nextLine().toUpperCase(Locale.ROOT);
            switch (actionCommand) {
                case "ADD":
                    addMenu(bookContacts, scanner);
                    break;
                case "COUNT":
                    bookContacts.getCountContacts();
                    break;
                case "LIST":
                    if (bookContacts.isEmpty()) {
                        System.out.println("No records!");
                    } else {
                        bookContacts.printShortInfo();
                        infoMenu(bookContacts, scanner);
                    }
                    break;
                case "SEARCH":
                    searchMenu(bookContacts, scanner);
                    break;
                case "EXIT":
                    exit = false;
                    SerializationUtils.writeObj(bookContacts);
                    break;
                default:
                    System.out.println("\n");
                    break;
            }
        } while (exit);

    }

    //Add Menu items
    private static void addMenu(BookContacts bookContacts, Scanner scanner) {

        System.out.println("Enter the type (person, organization):");
        String recordType = scanner.nextLine().toUpperCase(Locale.ROOT);
        switch (recordType) {
            case "PERSON":
                Person person = new Person();
                addPersonMenu(person, scanner);
                bookContacts.addRecord(person);
                break;
            case "ORGANIZATION":
                Organization organ = new Organization();
                addOrganizationMenu(organ, scanner);
                bookContacts.addRecord(organ);
                break;
            default:
                System.out.println();
                break;
        }
    }

    static void addPersonMenu(Person contact, Scanner scanner) {
        System.out.println("Enter the name:");
        String name = scanner.nextLine();
        contact.setName(name);
        System.out.println("Enter the surname:");
        String surname = scanner.nextLine();
        contact.setSurname(surname);
        System.out.println("Enter the birth date:");
        String birthDate = scanner.nextLine();
        contact.setBirthDate(birthDate);
        System.out.println("Enter the gender (M, F)");
        String gender = scanner.nextLine();
        contact.setGender(gender);
        System.out.println("Enter the number:");
        String phoneNumber = scanner.nextLine();
        contact.setNumber(phoneNumber);
    }

    private static void addOrganizationMenu(Organization organ, Scanner scanner) {
        System.out.println("Enter the organization name:");
        String nameOrgan = scanner.nextLine();
        organ.setNameOrgan(nameOrgan);
        System.out.println("Enter the address:");
        String address = scanner.nextLine();
        organ.setAddress(address);
        System.out.println("Enter the number:");
        String number = scanner.nextLine();
        organ.setNumber(number);
    }

    //Information Menu
    private static boolean infoMenu(BookContacts bookContacts, Scanner scanner) throws IOException {
        System.out.println("[list] Enter action ([number], back):");
        String listCommand = scanner.nextLine().toUpperCase(Locale.ROOT);
        List<SearchResult> searchResultList = new ArrayList<>();
        bookContacts.getListContacts().forEach(rec -> searchResultList.add(new SearchResult(rec, "")));

        if (listCommand.matches("\\b\\d+\\b")
                && ValidationUtil.isCorrectOrderNumber(bookContacts.getListContacts().size(), listCommand)) {
            if (ValidationUtil.isPersonClass(bookContacts.getContactToEdit(listCommand))) {
                Person person = (Person) bookContacts.getContactToEdit(listCommand);
                System.out.println(person);
            } else {
                Organization organ = (Organization) bookContacts.getContactToEdit(listCommand);
                System.out.println(organ);
            }
            recordMenu(listCommand, searchResultList, bookContacts, scanner);
            return true;
        } else if ("BACK".equals(listCommand)) {
            return true;
        } else {
            System.out.println("Incorrect command!");
        }
        return true;
    }

    //Record Menu items
    private static boolean recordMenu(String numberOrder, List<SearchResult> searchResult, BookContacts bookContacts, Scanner scanner) throws IOException {
        String recordMenu;
        do {
            System.out.println("[record] Enter action (edit, delete, menu):");
            recordMenu = scanner.nextLine().toUpperCase(Locale.ROOT);
            switch (recordMenu) {
                case "EDIT":
                    if (ValidationUtil.isPersonClass(searchResult.get(Integer.parseInt(numberOrder) - 1).getRecord())) {
                        Person person = (Person) searchResult.get(Integer.parseInt(numberOrder) - 1).getRecord();
                        editPersonMenu(person, scanner);
                    } else {
                        Organization organ = (Organization) searchResult.get(Integer.parseInt(numberOrder) - 1).getRecord();
                        editOrganizationMenu(organ, scanner);
                    }
                    break;
                case "DELETE":
                    bookContacts.deleteRecord(searchResult.get(Integer.parseInt(numberOrder) - 1).getRecord());
                    return true;
                case "MENU":
                    System.out.println();
                    return true;
                default:
                    System.out.println();
            }
        } while (true);
    }

    private static void editPersonMenu(Person person, Scanner scanner) {
        System.out.println("Select a field (name, surname, birth, gender, number):");
        String editCommand = scanner.nextLine().toUpperCase(Locale.ROOT);

        switch (editCommand) {
            case "NAME":
                System.out.println("Enter the name:");
                person.setName(scanner.nextLine());
                break;
            case "SURNAME":
                System.out.println("Enter the surname:");
                person.setSurname(scanner.nextLine());
                break;
            case "BIRTH":
                System.out.println("Enter the birth date:");
                person.setBirthDate(scanner.nextLine());
                break;
            case "GENDER":
                System.out.println("Enter the gender (M, F):");
                person.setGender(scanner.nextLine());
                break;
            case "NUMBER":
                System.out.println("Enter the number:");
                person.setNumber(scanner.nextLine());
                break;
        }
        System.out.println("Saved" + "\n");
        System.out.println(person);
    }

    private static void editOrganizationMenu(Organization organ, Scanner scanner) {
        System.out.println("Select a field (name, address, number):");
        String editCommand = scanner.nextLine().toUpperCase(Locale.ROOT);
        switch (editCommand) {
            case "NAME":
                System.out.println("Enter the organization name:");
                organ.setNameOrgan(scanner.nextLine());
                break;
            case "ADDRESS":
                System.out.println("Enter the address:");
                organ.setAddress(scanner.nextLine());
                break;
            case "NUMBER":
                System.out.println("Enter the number:");
                organ.setNumber(scanner.nextLine());
                break;
        }
        System.out.println("Saved" + "\n");
        System.out.println(organ);
    }

    //Search Menu items

    private static boolean searchMenu(BookContacts bookContacts, Scanner scanner) throws IOException {
        List<SearchResult> records;
        System.out.println("Enter search query:");

        String searchWord = scanner.nextLine();
        records = bookContacts.searchList(searchWord);

        System.out.println("[search] Enter action ([number], back, again):");
        String commandSearch = scanner.nextLine().toUpperCase(Locale.ROOT);
        if (commandSearch.matches("[1-9]\\d*")) {
            bookContacts.searchItem(records, commandSearch);
            recordMenu(commandSearch, records, bookContacts, scanner);
            return true;
        } else if (commandSearch.equals("BACK")) {
            return true;
        } else if (commandSearch.equals("AGAIN")) {
            searchMenu(bookContacts, scanner);
            return true;
        }
        return true;
    }
}
