package com.kvp.cache;

import com.kvp.web.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("globalCacheManager")
public class GlobalCacheManager {

    private List<User> userList;

    private Map<Integer,User> userIdMap;

    private Map<String,User> userNameMap;

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
