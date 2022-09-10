package com.streltsov.medium.task.model;

import com.streltsov.medium.task.utils.ValidationUtil;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;

public class Person extends Record implements Serializable {

    private String name;
    private String surname;
    private String gender;
    private String birthDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (ValidationUtil.isCorrectString(name)) {
            this.name = name;
            setUpdatedDate(LocalDateTime.now());
        } else {
            this.name = DEFAULT_VALUE;
            setUpdatedDate(LocalDateTime.now());
            System.out.println("Empty name!");
        }
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if (ValidationUtil.isCorrectString(surname)) {
            this.surname = surname;
            setUpdatedDate(LocalDateTime.now());
        } else {
            this.surname = DEFAULT_VALUE;
            setUpdatedDate(LocalDateTime.now());
            System.out.println("Empty surname!");
        }
    }

    public void setGender(String gender) {
        if (ValidationUtil.isCorrectGender(gender.toUpperCase(Locale.ROOT))) {
            this.gender = gender;
            setUpdatedDate(LocalDateTime.now());
        } else {
            this.gender = DEFAULT_VALUE;
            setUpdatedDate(LocalDateTime.now());
            System.out.println("Bad gender!");
        }
    }

    public void setBirthDate(String birthDate) {
        if (ValidationUtil.isCorrectBirthDate(birthDate)) {
            this.birthDate = birthDate;
            setUpdatedDate(LocalDateTime.now());
        } else {
            this.birthDate = DEFAULT_VALUE;
            setUpdatedDate(LocalDateTime.now());
            System.out.println("Bad birth date!");
        }
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Surname: " + surname + "\n" +
                "Birth date: " + birthDate + "\n" +
                "Gender: " + gender + "\n" +
                "Number: " + getNumber() + "\n" +
                "Time created: " + parseDateToString(getCreatingDate()) + "\n" +
                "Time last edit: " + parseDateToString(getUpdatedDate()) + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(surname, person.surname) && Objects.equals(gender, person.gender) && Objects.equals(birthDate, person.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, surname, gender, birthDate);
    }
}
