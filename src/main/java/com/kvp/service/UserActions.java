package com.kvp.service;

import com.kvp.dao.UserActionsDaoImpl;
import com.kvp.web.domain.Book;
import com.kvp.web.domain.BookMaster;
import com.kvp.web.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserActions {

    @Autowired
    @Qualifier("MyJdbcTempate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserActionsDaoImpl userActionsDao;

    public List<User> listUserToDatabase(String firstName, int userId) {

        return userActionsDao.listUserFromMemory(firstName, userId);
    }

    public String addUserToDatabase(User user) {

        userActionsDao.insertUserIntoDatabase(user);
        return "";
    }

    public String addBookMasterToDatabase(BookMaster bookMaster) {

        userActionsDao.insertBookMasterIntoDatabase(bookMaster);
        return "";
    }

    public String addBookToDatabase(Book book) {

        userActionsDao.insertBookIntoDatabase(book);
        return "";
    }
}
