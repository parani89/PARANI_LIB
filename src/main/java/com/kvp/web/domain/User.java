package com.kvp.web.domain;

import java.util.Date;

public class User {

    public User() {

    }

    public User(int id, String firstName, String lastName, String category, String gender, int bookLimit, String crtUser, String updUser, Date dateOfBirth, Date dateOfJoin) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.category = category;
        this.gender = gender;
        this.bookLimit = bookLimit;
        this.crtUser = crtUser;
        this.updUser = updUser;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoin = dateOfJoin;
    }

    int id;
    String firstName;
    String lastName;
    String category;
    String gender;
    Date dateOfBirth;
    Date dateOfJoin;
    Date dateofReturn;
    int bookLimit;
    String crtUser;
    Date crtTime;
    String updUser;
    Date updTime;
    int booksAvailed;
    String holdFlag;

    public String getHoldFlag() {
        return holdFlag;
    }

    public void setHoldFlag(String holdFlag) {
        this.holdFlag = holdFlag;
    }

    public int getBooksAvailed() {
        return booksAvailed;
    }

    public void setBooksAvailed(int booksAvailed) {
        this.booksAvailed = booksAvailed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfJoin() {
        return dateOfJoin;
    }

    public void setDateOfJoin(Date dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
    }

    public Date getDateofReturn() {
        return dateofReturn;
    }

    public void setDateofReturn(Date dateofReturn) {
        this.dateofReturn = dateofReturn;
    }

    public int getBookLimit() {
        return bookLimit;
    }

    public void setBookLimit(int bookLimit) {
        this.bookLimit = bookLimit;
    }

    public String getCrtUser() {
        return crtUser;
    }

    public void setCrtUser(String crtUser) {
        this.crtUser = crtUser;
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    public String getUpdUser() {
        return updUser;
    }

    public void setUpdUser(String updUser) {
        this.updUser = updUser;
    }

    public Date getUpdTime() {
        return updTime;
    }

    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }
}
