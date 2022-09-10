package com.streltsov.medium.task.model;

import com.streltsov.medium.task.utils.ValidationUtil;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Organization extends Record implements Serializable {

    private String nameOrgan;
    private String address;

    public String getNameOrgan() {
        return nameOrgan;
    }

    public String getAddress() {
        return address;
    }

    public void setNameOrgan(String nameOrgan) {
        if (ValidationUtil.isCorrectString(nameOrgan)) {
            this.nameOrgan = nameOrgan;
            setUpdatedDate(LocalDateTime.now());
        } else {
            this.nameOrgan = DEFAULT_VALUE;
            setUpdatedDate(LocalDateTime.now());
            System.out.println("Empty name!");
        }
    }

    public void setAddress(String address) {
        if (ValidationUtil.isCorrectString(address)) {
            this.address = address;
            setUpdatedDate(LocalDateTime.now());
        } else {
            this.address = DEFAULT_VALUE;
            setUpdatedDate(LocalDateTime.now());
            System.out.println("Empty address!");
        }
    }

    @Override
    public String toString() {
        return "Organization name: " + nameOrgan + "\n" +
                "Address: " + address + "\n" +
                "Number: " + getNumber() + "\n" +
                "Time created: " + parseDateToString(getCreatingDate()) + "\n" +
                "Time last edit: " + parseDateToString(getUpdatedDate()) + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Organization that = (Organization) o;
        return Objects.equals(nameOrgan, that.nameOrgan) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nameOrgan, address);
    }
}
