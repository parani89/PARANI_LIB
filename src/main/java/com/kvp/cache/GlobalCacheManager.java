package com.kvp.cache;

import com.kvp.web.domain.Book;
import com.kvp.web.domain.BookMaster;
import com.kvp.web.domain.BookRack;
import com.kvp.web.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("globalCacheManager")
public class GlobalCacheManager {

    private List<User> userList;

    private Map<Integer,User> userIdMap;

    private Map<String,User> userNameMap;

    private List<BookRack> bookRacks;

    private Map<Integer, Book> bookIdMap;

    private Map<Integer, BookRack> bookGrpIdMap;

    private Map<Integer, BookMaster> bookMasterMap;

    public Map<Integer, BookMaster> getBookMasterMap() {
        return bookMasterMap;
    }

    public void addBookMasterMap(Map<Integer, BookMaster> bookMasterMap) {
        this.bookMasterMap = bookMasterMap;
    }

    public Map<Integer, BookRack> getBookGrpIdMap() {
        return bookGrpIdMap;
    }

    public void addBookGrpIdMap(Map<Integer, BookRack> bookGrpIdMap) {
        this.bookGrpIdMap = bookGrpIdMap;
    }

    public void addBookIdMap(Map<Integer, Book> bookIdMap) {
        this.bookIdMap = bookIdMap;
    }

    public Map<Integer, Book> getBookIdMap() {
        return bookIdMap;
    }

    public List<BookRack> getBookRacks() {
        return bookRacks;
    }

    public void addBookRacks(List<BookRack> bookRacks) {
        this.bookRacks = bookRacks;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void addUserList(List<User> userList) {
        this.userList = userList;
    }

    public Map<Integer, User> getUserIdMap() {
        return userIdMap;
    }

    public void addUserIdMap(Map<Integer, User> userIdMap) {
        this.userIdMap = userIdMap;
    }

    public Map<String, User> getUserNameMap() {
        return userNameMap;
    }

    public void addUserNameMap(Map<String, User> userNameMap) {
        this.userNameMap = userNameMap;
    }


}
