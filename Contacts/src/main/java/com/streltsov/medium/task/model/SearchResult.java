package com.streltsov.medium.task.model;

import java.io.Serializable;
import java.util.Objects;

public class SearchResult implements Serializable {

    private final Record record;
    private final String matcherWord;

    public SearchResult(Record record, String matcherWord) {
        this.record = record;
        this.matcherWord = matcherWord;
    }

    public Record getRecord() {
        return record;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchResult that = (SearchResult) o;
        return Objects.equals(record, that.record) && Objects.equals(matcherWord, that.matcherWord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(record, matcherWord);
    }
}
