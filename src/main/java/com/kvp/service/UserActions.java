package com.kvp.service;

import com.kvp.cache.GlobalCacheManager;
import com.kvp.dao.UserActionsDaoImpl;
import com.kvp.domain.VgpTxn;
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

    @Autowired
    private GlobalCacheManager globalCacheManager;

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
        VgpTxn vgpTxn = new VgpTxn();
        userActionsDao.createTxnLog(user, vgpTxn, "Add user");
        userActionsDao.insertTxnLog(vgpTxn);
        return "User added to DataBase and inMemory";
    }

    public String deleteUserFromDatabase(User user) {

        if (kvpValidationService.validateUserExistance(user)) {
            if(! kvpValidationService.IsUserHoldingBooks(user.getId(), "user")) {
                userActionsDao.deleteUserFromDatabase(user);
                VgpTxn vgpTxn = new VgpTxn();
                userActionsDao.createTxnLog(user, vgpTxn, "Delete user");
                userActionsDao.insertTxnLog(vgpTxn);
            } else {
                return "User NOT deleted from DataBase and inMemory";
            }
        } else {
            return "User NOT exists in DataBase and inMemory";
        }

        return "User deleted from DataBase and inMemory";
    }

    public String deleteBookFromDatabase(Book book, BookMaster bookMaster) {

        if(kvpValidationService.isBookAlive(book.getBookId())) {

            if (kvpValidationService.validateBookExistance(book.getBookId())) {
                if (kvpValidationService.IsUserHoldingBooks(book.getBookId(), "book")) {
                    userActionsDao.deleteBookFromDatabaseAndInMemory(book.getBookId());
                    VgpTxn vgpTxn = new VgpTxn();
                    bookMaster.setBookName(globalCacheManager.getBookMasterMap().get(globalCacheManager.getBookIdMap().get(book.getBookId()).getBookGroupId()).getBookName());
                    userActionsDao.createTxnLog(book, bookMaster, vgpTxn, "Delete book");
                    userActionsDao.insertTxnLog(vgpTxn);
                } else {
                    return "Book NOT deleted from DataBase and inMemory";
                }
            } else {
                return "Book NOT exists in DataBase and inMemory";
            }

            return "Book deleted from DataBase and inMemory";
        } else {
            return "Book already inactive/deleted";
        }
    }

    public String addBookMasterToDatabase(Book book, BookMaster bookMaster) {

        String responseString;
        if(kvpValidationService.validateBookMasterAvailability(bookMaster)) {
            responseString = userActionsDao.updateBookMasterIntoDatabase(bookMaster);
        } else {
            responseString = userActionsDao.insertBookMasterIntoDatabase(bookMaster);
        }

        VgpTxn vgpTxn = new VgpTxn();
        userActionsDao.createTxnLog(book, bookMaster, vgpTxn, "Add book");
        userActionsDao.insertTxnLog(vgpTxn);

        return responseString;
    }

    public String addBookToDatabase(Book book) {

        String responseString = userActionsDao.insertBookIntoDatabase(book);
        return responseString;
    }

    public String addBookInMemory(Book book, BookMaster bookMaster) {

        String responseString = userActionsDao.insertBookInMemory(book, bookMaster);
        return responseString;
    }
}
