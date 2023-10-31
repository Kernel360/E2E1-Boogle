package com.kernel360.boogle.book.model;

public enum BookSearchType {
    TITLE("title"),
    AUTHOR("author"),
    PUBLISHER("publisher");

    private String type;

    BookSearchType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
