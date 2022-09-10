package com.streltsov.medium.task.model;

import com.streltsov.medium.task.utils.ValidationUtil;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Record implements Serializable {

    protected static final long serialVersionUID = 42L;
    private String number;
    protected static final String DEFAULT_VALUE = "[no data]";
    private final LocalDateTime creatingDate = LocalDateTime.now();
    private LocalDateTime updatedDate = LocalDateTime.now();

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        if (ValidationUtil.isCorrectPhoneNumber(number)) {
            this.number = number;
        } else {
            this.number = DEFAULT_VALUE;
        }
        this.updatedDate = LocalDateTime.now();
    }

    protected LocalDateTime getCreatingDate() {
        return creatingDate;
    }

    protected void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    protected String parseDateToString(LocalDateTime dateTime) {
        String date = dateTime.toString();
        return date.substring(0, 16);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return Objects.equals(number, record.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
