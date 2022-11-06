package com.kvp.dao;

public class UserActionsDao {

    public static final String USER_INSERT = "insert into vgp_users values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
    public static final String USER_DELETE = "update vgp_users set hold_flag='Y' where id=?";
    public static final String BOOK_INSERT = "insert into vgp_books values(?,?,?,?,?,sysdate,?,sysdate,'Y')";
    public static final String BOOK_DELETE = "update vgp_books set alive='N' where book_id=?";
    public static final String BOOK_MASTER_DELETE = "update vgp_book_master set alive='N' where book_grp_id=?";
    public static final String BOOK_MASTER_INSERT = "insert into vgp_book_master values(?,?,?,?,1,1,?,sysdate,?,sysdate,'Y')";
    public static final String BOOK_MASTER_UPDATE = "update vgp_book_master set no_of_copy=no_of_copy+1, available_copy=available_copy+1 where book_grp_id=?";

    public static final String REMOVE_COPIES = "update vgp_book_master set no_of_copy=no_of_copy - 1, available_copy=available_copy - 1 where book_grp_id=?";
    public static final String TXN_INSERT = "insert into vgp_txn values(vgp_txn_seq.nextval,?,?,?,?,?,?,?,?)";
}
