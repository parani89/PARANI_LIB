package com.kvp.dao;

import com.kvp.cache.GlobalCacheManager;
import com.kvp.web.domain.Book;
import com.kvp.web.domain.BookMaster;
import com.kvp.web.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.kvp.bookmgmt.service.dao.UserActionsDao.BOOK_INSERT;
import static com.kvp.bookmgmt.service.dao.UserActionsDao.BOOK_MASTER_INSERT;
import static com.kvp.bookmgmt.service.dao.UserActionsDao.USER_INSERT;

@Service
public class UserActionsDaoImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private GlobalCacheManager globalCacheManager;

    public void insertUserIntoDatabase(User user) {

        jdbcTemplate.update(USER_INSERT, user.getId(), user.getFirstName(), user.getLastName(), user.getCategory(), user.getGender(), user.getDateOfBirth(),
        user.getDateOfJoin(), user.getDateofReturn(), user.getBookLimit(), user.getCrtUser(), user.getUpdUser());

        System.out.println("Inserted to table "+user.getFirstName());
    }

    public void insertBookMasterIntoDatabase(BookMaster bookMaster) {

        jdbcTemplate.update(BOOK_MASTER_INSERT, bookMaster.getBookGroupId(),bookMaster.getBookName(),bookMaster.getAuthor(),bookMaster.getYear(),bookMaster.getCrtUser(),bookMaster.getUpdUser());

        System.out.println("Inserted to table "+bookMaster.getBookName());
    }

    public void insertBookIntoDatabase(Book book) {

        jdbcTemplate.update(BOOK_INSERT, book.getBookId(), book.getBookGroupId(), book.getAvailability(), book.getUserHolding(), book.getCrtUser(), book.getUpdUser());

        System.out.println("Inserted to table "+book.getBookId());
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
}