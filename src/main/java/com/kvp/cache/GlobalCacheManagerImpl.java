package com.kvp.cache;

import com.kvp.web.domain.Book;
import com.kvp.web.domain.BookMaster;
import com.kvp.web.domain.BookRack;
import com.kvp.web.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GlobalCacheManagerImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private GlobalCacheManager globalCacheManager;

    public void getUserFromDatabase() {

        List<User> allUserList = new ArrayList<>();
        Map<Integer, User> userIdMap = new HashMap<>();
        Map<String, User> userNameMap = new HashMap<>();

        String sql="select * from vgp_users";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        for(Map row : rows) {

            User user1 = new User();
            user1.setId(1);
            user1.setFirstName((String) row.get("first_name"));
            user1.setLastName((String) row.get("last_name"));
            user1.setCategory((String) row.get("category"));
            user1.setGender((String) row.get("gender"));
            user1.setDateOfBirth((Date) row.get("dob"));
            user1.setDateOfJoin((Date) row.get("doj"));
            user1.setDateofReturn((Date) row.get("dor"));
            user1.setBookLimit(1);
            user1.setCrtTime((Date) row.get("crt_ts"));
            user1.setUpdTime((Date) row.get("upd_ts"));
            user1.setCrtUser((String) row.get("crt_usr"));
            user1.setCrtUser((String) row.get("upd_usr"));

            allUserList.add(user1);
            userIdMap.put(user1.getId(),user1);
            userNameMap.put(user1.getFirstName(), user1);
        }

        globalCacheManager.addUserList(allUserList);
        globalCacheManager.addUserIdMap(userIdMap);
        globalCacheManager.addUserNameMap(userNameMap);

    }

    public void getBookInfoFromDatabase() {

        String sql="select * from vgp_books a, vgp_book_master b where a.book_grp_id=b.book_grp_id order by book_id";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        List<BookRack> bookRacks = new ArrayList<>();
        List<Book> books = new ArrayList<>();
        List<BookMaster> bookMasters = new ArrayList<>();
        Map<Integer, Book> bookIdMap = new HashMap<>();
        Map<Integer, BookRack> bookGrpIdMap = new HashMap<>();

        for(Map row : rows) {

            BookRack bookRack = new BookRack();
            Book book = new Book();
            BookMaster bookMaster = new BookMaster();

            book.setBookId(1);
            book.setBookGroupId(1);
            book.setUserHolding((String) row.get("user_holding"));
            book.setAvailability((String) row.get("availability"));
            book.setCrtTime((Date) row.get("crt_ts"));
            book.setUpdTime((Date) row.get("upd_ts"));
            book.setCrtUser((String) row.get("crt_usr"));
            book.setUpdUser((String) row.get("upd_usr"));

            books.add(book);
            bookIdMap.put(book.getBookId(),book);

            bookMaster.setAuthor((String) row.get("book_author"));
            bookMaster.setAvailableCopies(1);
            bookMaster.setBookName((String) row.get("book_name"));
            bookMaster.setBookGroupId(book.getBookGroupId());
            bookMaster.setCopies(1);
            bookMaster.setYear(1);
            bookMaster.setCrtTime((Date) row.get("crt_ts"));
            bookMaster.setUpdTime((Date) row.get("crt_ts"));
            bookMaster.setCrtUser((String) row.get("crt_usr"));
            bookMaster.setUpdUser((String) row.get("upd_usr"));

            bookMasters.add(bookMaster);

            bookRack.setAuthor(bookMaster.getAuthor());
            bookRack.setAvailability(book.getAvailability());
            bookRack.setAvailableCopies(bookMaster.getAvailableCopies());
            bookRack.setBookGroupId(book.getBookGroupId());
            bookRack.setBookId(book.getBookId());
            bookRack.setBookName(bookMaster.getBookName());
            bookRack.setUserHolding(book.getUserHolding());
            bookRack.setYear(bookMaster.getYear());
            bookRack.setCopies(bookMaster.getCopies());

            bookRacks.add(bookRack);
            bookGrpIdMap.put(book.getBookGroupId(),bookRack);
        }

        globalCacheManager.addBookRacks(bookRacks);
    }
}
