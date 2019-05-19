package com.kvp.bookmgmt.service;

import com.kvp.bookmgmt.service.dao.UserActionsDaoImpl;
import com.kvp.web.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class UserActions {

    @Autowired
    @Qualifier("MyJdbcTempate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserActionsDaoImpl userActionsDao;

    public List<User> listUserToDatabase(String firstName, int userId) {

        return userActionsDao.listUserFromDatabase(firstName, userId);
    }

    public String addUserToDatabase(User user) {

        userActionsDao.insertUserIntoDatabase(user);
        return "";
    }
}
