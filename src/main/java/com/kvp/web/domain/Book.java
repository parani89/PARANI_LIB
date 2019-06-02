package com.kvp.web.domain;

import java.util.Date;

public class Book {

    int bookId;
    int bookGroupId;
    String availability;
    String userHolding;
    String crtUser;
    String updUser;
    Date crtTime;
    Date updTime;
    String alive;

    public Book() {

    }

    public Book(int bookId, int bookGroupId, String availability, String userHolding, String crtUser, String updUser, String alive) {
        this.bookId = bookId;
        this.bookGroupId = bookGroupId;
        this.availability = availability;
        this.userHolding = userHolding;
        this.crtUser = crtUser;
        this.updUser = updUser;
        this.setAlive(alive);
    }

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
