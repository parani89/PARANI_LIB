package com.kvp.domain;

import java.util.Date;

public class VgpTxn {

    int seqNo;
    int bookId;
    int bookGrpId;
    String bookName;
    String msgTx;
    int userId;
    String userFirstName;
    String userLastName;
    String crtUser;
    Date crtTs;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getBookGrpId() {
        return bookGrpId;
    }

    public void setBookGrpId(int bookGrpId) {
        this.bookGrpId = bookGrpId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getMsgTx() {
        return msgTx;
    }

    public void setMsgTx(String msgTx) {
        this.msgTx = msgTx;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getCrtUser() {
        return crtUser;
    }

    public void setCrtUser(String crtUser) {
        this.crtUser = crtUser;
    }

    public Date getCrtTs() {
        return crtTs;
    }

    public void setCrtTs(Date crtTs) {
        this.crtTs = crtTs;
    }
}
