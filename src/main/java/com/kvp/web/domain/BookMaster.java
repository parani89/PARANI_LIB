package com.kvp.web.domain;

import java.util.Date;

public class BookMaster {

    int bookGroupId;
    String bookName;
    String author;
    int year;
    int copies;
    int availableCopies;
    String crtUser;
    String updUser;
    Date crtTime;
    Date updTime;
    String alive;


    public BookMaster() {

    }

    public BookMaster(int bookGroupId, String bookName, String author, int year, String crtUser, String updUser, String alive) {
        this.bookGroupId = bookGroupId;
        this.bookName = bookName;
        this.author = author;
        this.year = year;
        this.crtUser = crtUser;
        this.updUser = updUser;
        this.alive = alive;
    }

    public int getBookGroupId() {
        return bookGroupId;
    }

    public void setBookGroupId(int bookGroupId) {
        this.bookGroupId = bookGroupId;
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

    public String getCrtUser() {
        return crtUser;
    }

    public void setCrtUser(String crtUser) {
        this.crtUser = crtUser;
    }

    public String getUpdUser() {
        return updUser;
    }

    public void setUpdUser(String updUser) {
        this.updUser = updUser;
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    public Date getUpdTime() {
        return updTime;
    }

    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    public String getAlive() {
        return alive;
    }

    public void setAlive(String alive) {
        this.alive = alive;
    }
}
