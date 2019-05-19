package com.kvp.bookmgmt.service.dao;

public class UserActionsDao {

    public static final String USER_INSERT = "insert into vgp_users values(?,?,?,?,?,?,?,?,?,?,sysdate,?,sysdate)";
    public static final String BOOK_INSERT = "insert into vgp_books values(?,?,?,?,?,sysdate,?,sysdate)";
    public static final String BOOK_MASTER_INSERT = "insert into vgp_book_master values(?,?,?,?,1,1,?,sysdate,?,sysdate)";

}
