package com.kvp.dao;

import com.kvp.cache.GlobalCacheManager;
import com.kvp.domain.VgpTxn;
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
        user.getDateOfJoin(), user.getDateofReturn(), user.getBookLimit(), user.getCrtUser(), user.getUpdUser(), user.getHoldFlag(), user.getBooksAvailed());

        addUserInMemory(user);

       // System.out.println("Inserted to table "+user.getFirstName());
    }

    public void deleteUserFromDatabase(User user) {

        jdbcTemplate.update(USER_DELETE, user.getId());

        System.out.println("User Marked as Hold"+user.getId());
    }

    public void deleteBookFromDatabaseAndInMemory(int bookId) {

        jdbcTemplate.update(BOOK_DELETE, bookId);

        adjustTotalBookCopyInMemoryAndDatabase(bookId, '-');

        System.out.println("Book Marked as Hold"+bookId);
    }

    public void createTxnLog(User user, VgpTxn vgpTxn, String msgTx) {

        vgpTxn.setMsgTx(msgTx);
        vgpTxn.setUserId(user.getId());
        vgpTxn.setUserFirstName(user.getFirstName());
        vgpTxn.setUserLastName(user.getLastName());
        vgpTxn.setCrtTs(user.getCrtTime());
        vgpTxn.setCrtUser(user.getCrtUser());

        System.out.println("Txn Journal created");

    }

    public void createTxnLog(Book book, BookMaster bookMaster, VgpTxn vgpTxn, String msgTx) {

        vgpTxn.setMsgTx(msgTx);

        vgpTxn.setBookId(book.getBookId());
        vgpTxn.setBookGrpId(book.getBookGroupId());
        vgpTxn.setBookName(bookMaster.getBookName());
        vgpTxn.setCrtTs(book.getCrtTime());
        vgpTxn.setCrtUser(book.getCrtUser());

        System.out.println("Txn Journal created");

    }

    public void insertTxnLog(VgpTxn vgpTxn) {

        jdbcTemplate.update(TXN_INSERT, vgpTxn.getBookId(), vgpTxn.getBookGrpId(), vgpTxn.getBookName(), vgpTxn.getMsgTx(), vgpTxn.getUserFirstName(), vgpTxn.getUserLastName(), vgpTxn.getCrtUser(), vgpTxn.getCrtTs());

        System.out.println("Txn Journal updated");

    }

    private void adjustTotalBookCopyInMemoryAndDatabase(int bookId, char c) {

        int bookGrpId = globalCacheManager.getBookIdMap().get(bookId).getBookGroupId();
        int noOfCopies=globalCacheManager.getBookGrpIdMap().get(bookGrpId).getCopies();
        int availableCopies=globalCacheManager.getBookGrpIdMap().get(bookGrpId).getAvailableCopies();

        if(noOfCopies >=1 ) {
            jdbcTemplate.update(REMOVE_COPIES, bookGrpId);
            adjustTotalCopiesInMemory(c, bookId, bookGrpId, noOfCopies, availableCopies);
        }
    }

    private void adjustTotalCopiesInMemory(char c, int bookId, int bookGrpId, int noOfCopies, int availableCopies) {

        if(c == '+') {
            globalCacheManager.getBookGrpIdMap().get(bookGrpId).setCopies(noOfCopies+1);
            globalCacheManager.getBookGrpIdMap().get(bookGrpId).setAvailableCopies(availableCopies+1);
        } else {
            globalCacheManager.getBookIdMap().get(bookId).setAlive("N");
            globalCacheManager.getBookGrpIdMap().get(bookGrpId).setCopies(noOfCopies-1);
            globalCacheManager.getBookGrpIdMap().get(bookGrpId).setAvailableCopies(availableCopies-1);
            if(noOfCopies==1) {
                jdbcTemplate.update(BOOK_MASTER_DELETE, bookGrpId);
                globalCacheManager.getBookMasterMap().get(bookGrpId).setAlive("N");
            }
        }
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

    public String insertBookInMemory(Book book, BookMaster bookMaster) {

        if(globalCacheManager.getBookGrpIdMap().get(book.getBookGroupId()) != null) {
            bookMaster.setAvailableCopies(globalCacheManager.getBookGrpIdMap().get(bookMaster.getBookGroupId()).getAvailableCopies() + 1);
            bookMaster.setCopies(globalCacheManager.getBookGrpIdMap().get(bookMaster.getBookGroupId()).getAvailableCopies() + 1);
        }
        BookRack bookRack = new BookRack();
        populateBookRack(book, bookMaster, bookRack);
        globalCacheManager.getBookIdMap().put(book.getBookId(), book);
        globalCacheManager.getBookMasterMap().put(bookMaster.getBookGroupId(),bookMaster);
        globalCacheManager.getBookGrpIdMap().put(bookRack.getBookGroupId(), bookRack);
        globalCacheManager.getBookRacks().add(bookRack);
        return "Book added to database and memory";
    }

    public void populateBookRack(Book book, BookMaster bookMaster, BookRack bookRack) {
        bookRack.setAuthor(bookMaster.getAuthor());
        bookRack.setAvailability(book.getAvailability());
        bookRack.setAvailableCopies(bookMaster.getAvailableCopies());
        bookRack.setBookGroupId(book.getBookGroupId());
        bookRack.setBookId(book.getBookId());
        bookRack.setBookName(bookMaster.getBookName());
        bookRack.setUserHolding(book.getUserHolding());
        bookRack.setYear(bookMaster.getYear());
        bookRack.setCopies(bookMaster.getCopies());
    }
}