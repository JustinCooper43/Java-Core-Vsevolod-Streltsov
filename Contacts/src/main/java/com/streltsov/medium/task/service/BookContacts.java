package com.streltsov.medium.task.service;

import com.streltsov.medium.task.model.Organization;
import com.streltsov.medium.task.model.Person;
import com.streltsov.medium.task.model.Record;
import com.streltsov.medium.task.model.SearchResult;
import com.streltsov.medium.task.utils.ValidationUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookContacts implements Serializable {

    public static final long UUId = 768459301241L;
    private final List<Record> listContacts;

    public BookContacts() {
        listContacts = new ArrayList<>();
    }

    public void addRecord(Record contact) {
        listContacts.add(0, contact);
        System.out.println("A record added." + "\n");
    }

    public void getCountContacts() {
        final int size = listContacts.size();
        System.out.println("The Phone Book has " + size + " records.");
    }

    public Record getContactToEdit(String numberOrderContact) {
        if (ValidationUtil.isCorrectOrderNumber(listContacts.size(), numberOrderContact)) {
            return listContacts.get(Integer.parseInt(numberOrderContact) - 1);
        } else {
            System.out.println("No records to edit!");
            return null;
        }
    }

    public void printShortInfo() {
        for (int i = 0; i < listContacts.size(); i++) {
            Object obj = listContacts.get(i);
            if (ValidationUtil.isPersonClass(obj)) {
                Person contact = (Person) listContacts.get(i);
                System.out.printf("%d. %s %s \n", i + 1, contact.getName(), contact.getSurname());
            } else {
                Organization organ = (Organization) listContacts.get(i);
                System.out.printf("%d. %s  \n", i + 1, organ.getNameOrgan());
            }
        }
        System.out.println();
    }

    public boolean isEmpty() {
        return listContacts.isEmpty();
    }

    public List<SearchResult> searchList(String wordSearch) {
        List<SearchResult> searchListResult = new ArrayList<>();
        Set<SearchResult> setSearchResult = new LinkedHashSet<>();
        for (Record contact : listContacts) {
            if (ValidationUtil.isPersonClass(contact)) {
                Person person = (Person) contact;
                String fullInfoPerson = person.getName() + " " +
                        person.getSurname() + " " +
                        person.getNumber();
                final Matcher matcherPersonName = Pattern.compile(wordSearch, Pattern.CASE_INSENSITIVE).matcher(fullInfoPerson);
                if (matcherPersonName.find()) {
                    searchListResult.add(new SearchResult(person, matcherPersonName.group()));
                    setSearchResult.add(new SearchResult(person, matcherPersonName.group()));
                }

            } else {
                Organization organ = (Organization) contact;
                String fullInfoOrgan = organ.getNameOrgan() + " " +
                        organ.getNumber() + " " +
                        organ.getAddress();

                Matcher matcherOrgan = Pattern.compile(wordSearch, Pattern.CASE_INSENSITIVE).matcher(fullInfoOrgan);
                if (matcherOrgan.find()) {
                    searchListResult.add(new SearchResult(organ, matcherOrgan.group()));
                    setSearchResult.add(new SearchResult(organ, matcherOrgan.group()));
                }
            }
        }

        List<SearchResult> listSetSearchResult = new ArrayList<>(setSearchResult);
        printSearchList(listSetSearchResult);
        return listSetSearchResult;
    }

    private void printSearchList(List<SearchResult> searchListResult) {
        System.out.println("Found " + searchListResult.size() + " results:");
        for (int i = 0; i < searchListResult.size(); i++) {
            if (ValidationUtil.isPersonClass(searchListResult.get(i).getRecord())) {
                Person person = (Person) searchListResult.get(i).getRecord();
                System.out.println(i + 1 + ". " + person.getName() + " " + person.getSurname());
            } else {
                Organization organ = (Organization) searchListResult.get(i).getRecord();
                System.out.println(i + 1 + ". " + organ.getNameOrgan());
            }
        }
    }

    public void searchItem(List<SearchResult> searchListResult, String numberOrder) {
        if (ValidationUtil.isCorrectOrderNumber(searchListResult.size(), numberOrder)) {
            int index = Integer.parseInt(numberOrder) - 1;
            if (ValidationUtil.isPersonClass(searchListResult.get(index).getRecord())) {
                Person person = (Person) searchListResult.get(index).getRecord();
                System.out.println(person);
            } else {
                Organization organ = (Organization) searchListResult.get(index).getRecord();
                System.out.println(organ);
            }
        }
    }

    public List<Record> getListContacts() {
        return listContacts;
    }

    public void deleteRecord(Record record) {
        listContacts.remove(record);
    }

}
