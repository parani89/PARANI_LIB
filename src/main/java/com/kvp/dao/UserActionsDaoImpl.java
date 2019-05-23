package com.kvp.dao;

import com.kvp.cache.GlobalCacheManager;
import com.kvp.web.domain.Book;
import com.kvp.web.domain.BookMaster;
import com.kvp.web.domain.BookRack;
import com.kvp.web.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.kvp.dao.UserActionsDao.*;

@Service
public class UserActionsDaoImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private GlobalCacheManager globalCacheManager;

    public void insertUserIntoDatabase(User user) {

        jdbcTemplate.update(USER_INSERT, user.getId(), user.getFirstName(), user.getLastName(), user.getCategory(), user.getGender(), user.getDateOfBirth(),
        user.getDateOfJoin(), user.getDateofReturn(), user.getBookLimit(), user.getCrtUser(), user.getUpdUser());

        addUserInMemory(user);

        System.out.println("Inserted to table "+user.getFirstName());
    }

    private void addUserInMemory(User user) {
        globalCacheManager.getUserList().add(user);
        globalCacheManager.getUserIdMap().put(user.getId(),user);
        globalCacheManager.getUserNameMap().put(user.getFirstName(),user);
    }

    public String insertBookMasterIntoDatabase(BookMaster bookMaster) {

        jdbcTemplate.update(BOOK_MASTER_INSERT, bookMaster.getBookGroupId(),bookMaster.getBookName(),bookMaster.getAuthor(),bookMaster.getYear(),bookMaster.getCrtUser(),bookMaster.getUpdUser());

        return "Inserted to table "+bookMaster.getBookName();
    }

    public String updateBookMasterIntoDatabase(BookMaster bookMaster) {

        jdbcTemplate.update(BOOK_MASTER_UPDATE, bookMaster.getBookGroupId());

        return "Updated to BookMaster table "+bookMaster.getBookName();
    }

    public String insertBookIntoDatabase(Book book) {

        jdbcTemplate.update(BOOK_INSERT, book.getBookId(), book.getBookGroupId(), book.getAvailability(), book.getUserHolding(), book.getCrtUser(), book.getUpdUser());

        return "Inserted to books table "+book.getBookId();
    }

    public List<User> listUserFromMemory(String firstName, int userId) {

        List<User> users = new ArrayList<>();
        if(firstName == null && userId == 0 ) {
            return globalCacheManager.getUserList();
        } else if (userId > 0){
            users.add(globalCacheManager.getUserIdMap().get(userId));
            return users;
        } else {
            users.add(globalCacheManager.getUserNameMap().get(firstName));
            return users;
        }
    }

    public List<BookRack> listBookRackFromMemory(String bookName, int bookGrpId) {

        List<BookRack> bookRacks = new ArrayList<>();
        if(bookName == null && bookGrpId == 0 ) {
            return globalCacheManager.getBookRacks();

        } else if (bookGrpId > 0){
            bookRacks.add(globalCacheManager.getBookGrpIdMap().get(bookGrpId));
            return bookRacks;
        } else {
            return bookRacks;
        }
    }
}