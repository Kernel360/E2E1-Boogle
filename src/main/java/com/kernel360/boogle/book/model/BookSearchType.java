package com.kernel360.boogle.book.model;

public enum BookSearchType {
    TITLE("title"),
    AUTHOR("author"),
    PUBLISHER("publisher");

    private final String type;

    BookSearchType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public boolean isTitle() {
        return this == TITLE;
    }

    public boolean isAuthor() {
        return this == AUTHOR;
    }

    public boolean isPublisher() {
        return this == PUBLISHER;
    }
}
