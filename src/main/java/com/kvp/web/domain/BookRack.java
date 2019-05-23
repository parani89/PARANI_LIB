package com.kvp.web.domain;

public class BookRack {

    int bookId;
    int bookGroupId;
    String availability;
    String userHolding;
    String bookName;
    String author;
    int year;
    int copies;
    int availableCopies;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getBookGroupId() {
        return bookGroupId;
    }

    public void setBookGroupId(int bookGroupId) {
        this.bookGroupId = bookGroupId;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getUserHolding() {
        return userHolding;
    }

    public void setUserHolding(String userHolding) {
        this.userHolding = userHolding;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }
}
