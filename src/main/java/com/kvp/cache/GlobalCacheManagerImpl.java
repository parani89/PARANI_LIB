package com.kvp.cache;

import com.kvp.web.domain.Book;
import com.kvp.web.domain.BookMaster;
import com.kvp.web.domain.BookRack;
import com.kvp.web.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
public class GlobalCacheManagerImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private GlobalCacheManager globalCacheManager;

    public void loadUserFromDatabase() {

        List<User> allUserList = new ArrayList<>();
        Map<Integer, User> userIdMap = new HashMap<>();
        Map<String, User> userNameMap = new HashMap<>();

        String sql="select * from vgp_users where hold_flag='N'";

        List<Map<String, Object>> rows = jdbcTemplate.query(sql, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet resultSet, int i) throws SQLException {
                User user1 = new User();
                user1.setId(resultSet.getInt("id"));
                user1.setFirstName(resultSet.getString("first_name"));
                user1.setLastName(resultSet.getString("last_name"));
                user1.setCategory(resultSet.getString("category"));
                user1.setGender(resultSet.getString("gender"));
                user1.setDateOfBirth(resultSet.getDate("dob"));
                user1.setDateOfJoin(resultSet.getDate("doj"));
                user1.setBooksAvailed(resultSet.getInt("book_availed"));
                user1.setHoldFlag(resultSet.getString("hold_flag"));
                user1.setDateofReturn(resultSet.getDate("dor"));
                user1.setBookLimit(resultSet.getInt("book_limit"));
                user1.setCrtTime(resultSet.getDate("crt_ts"));
                user1.setUpdTime(resultSet.getDate("upd_ts"));
                user1.setCrtUser(resultSet.getString("crt_usr"));
                user1.setCrtUser(resultSet.getString("upd_usr"));

                allUserList.add(user1);
                userIdMap.put(user1.getId(),user1);
                userNameMap.put(user1.getFirstName(), user1);
                return null;
            }
        });

        globalCacheManager.addUserList(allUserList);
        globalCacheManager.addUserIdMap(userIdMap);
        globalCacheManager.addUserNameMap(userNameMap);

    }

    public void loadBookInfoFromDatabase() {

        String sql = "select * from vgp_books a, vgp_book_master b where a.book_grp_id=b.book_grp_id order by book_id";

        List<BookRack> bookRacks = new ArrayList<>();
        List<Book> books = new ArrayList<>();
        Map<Integer, BookMaster> bookMastersMap = new HashMap<>();
        Map<Integer, Book> bookIdMap = new HashMap<>();
        Map<Integer, BookRack> bookGrpIdMap = new HashMap<>();

        List<Map<String, Object>> rows = jdbcTemplate.query(sql, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet resultSet, int i) throws SQLException {

                BookRack bookRack = new BookRack();
                Book book = new Book();
                BookMaster bookMaster = new BookMaster();

                book.setBookId(resultSet.getInt("book_id"));
                book.setBookGroupId(resultSet.getInt("book_grp_id"));
                book.setUserHolding(resultSet.getString("user_holding"));
                book.setAvailability(resultSet.getString("availability"));
                book.setCrtTime(resultSet.getDate("crt_ts"));
                book.setUpdTime(resultSet.getDate("upd_ts"));
                book.setCrtUser(resultSet.getString("crt_usr"));
                book.setUpdUser(resultSet.getString("upd_ts"));
                book.setAlive(resultSet.getString("alive"));

                books.add(book);
                bookIdMap.put(book.getBookId(), book);

                bookMaster.setAuthor(resultSet.getString("book_author"));
                bookMaster.setAvailableCopies(resultSet.getInt("available_copy"));
                bookMaster.setBookName(resultSet.getString("book_name"));
                bookMaster.setBookGroupId(book.getBookGroupId());
                bookMaster.setCopies(resultSet.getInt("no_of_copy"));
                bookMaster.setYear(resultSet.getInt("year"));
                bookMaster.setCrtTime(resultSet.getDate("crt_ts"));
                bookMaster.setUpdTime(resultSet.getDate("upd_ts"));
                bookMaster.setCrtUser(resultSet.getString("crt_usr"));
                bookMaster.setUpdUser(resultSet.getString("upd_usr"));
                bookMaster.setAlive(resultSet.getString("alive"));

                bookMastersMap.put(bookMaster.getBookGroupId(),bookMaster);


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
                bookGrpIdMap.put(book.getBookGroupId(), bookRack);

                return null;
            }
        });

        globalCacheManager.addBookRacks(bookRacks);
        globalCacheManager.addBookMasterMap(bookMastersMap);
        globalCacheManager.addBookGrpIdMap(bookGrpIdMap);
        globalCacheManager.addBookIdMap(bookIdMap);
    }
}