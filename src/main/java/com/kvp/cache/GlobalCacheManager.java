package com.kvp.cache;

import com.kvp.web.domain.Book;
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

    private Map<Integer, BookRack> bookIdMap;

    private Map<Integer, BookRack> bookGrpIdMap;

    public Map<Integer, BookRack> getBookGrpIdMap() {
        return bookGrpIdMap;
    }

    public void addBookGrpIdMap(Map<Integer, BookRack> bookGrpIdMap) {
        this.bookGrpIdMap = bookGrpIdMap;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public void setUserIdMap(Map<Integer, User> userIdMap) {
        this.userIdMap = userIdMap;
    }

    public void setUserNameMap(Map<String, User> userNameMap) {
        this.userNameMap = userNameMap;
    }

    public void setBookRacks(List<BookRack> bookRacks) {
        this.bookRacks = bookRacks;
    }

    public Map<Integer, BookRack> getBookIdMap() {
        return bookIdMap;
    }

    public void setBookIdMap(Map<Integer, BookRack> bookIdMap) {
        this.bookIdMap = bookIdMap;
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
