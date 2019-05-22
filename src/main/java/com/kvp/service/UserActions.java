package com.kvp.service;

import com.kvp.dao.UserActionsDaoImpl;
import com.kvp.web.domain.Book;
import com.kvp.web.domain.BookMaster;
import com.kvp.web.domain.BookRack;
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

    @Autowired
    private KvpValidationService kvpValidationService;

    public List<User> listUserFromDatabase(String firstName, int userId) {

        return userActionsDao.listUserFromMemory(firstName, userId);
    }

    public List<BookRack> listBookFromDatabase(String bookName, int bookGrpId) {

        return userActionsDao.listBookRackFromMemory(bookName, bookGrpId);
    }

    public String addUserToDatabase(User user) {

        if (kvpValidationService.validateUserExistance(user)) {
            return "User already exists inMemory.. Returning back";
        }
        userActionsDao.insertUserIntoDatabase(user);
        return "User added to DataBase and inMemory";
    }

    public String addBookMasterToDatabase(BookMaster bookMaster) {

        String responseString;
        if(kvpValidationService.validateBookMasterAvailability(bookMaster)) {
            responseString = userActionsDao.updateBookMasterIntoDatabase(bookMaster);
        } else {
            responseString = userActionsDao.insertBookMasterIntoDatabase(bookMaster);
        }
        return responseString;
    }

    public String addBookToDatabase(Book book) {

        String responseString = userActionsDao.insertBookIntoDatabase(book);
        return responseString;
    }
}
