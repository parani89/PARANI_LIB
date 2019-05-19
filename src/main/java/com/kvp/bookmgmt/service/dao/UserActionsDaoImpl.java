package com.kvp.bookmgmt.service.dao;

import com.kvp.web.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.kvp.bookmgmt.service.dao.UserActionsDao.USER_INSERT;

@Service
public class UserActionsDaoImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertUserIntoDatabase(User user) {

        jdbcTemplate.update(USER_INSERT, user.getId(), user.getFirstName(), user.getLastName(), user.getCategory(), user.getGender(), user.getDateOfBirth(),
        user.getDateOfJoin(), user.getDateofReturn(), user.getBookLimit(), user.getCrtUser(), user.getUpdUser());

        System.out.println("Inserted to table "+user.getFirstName());
    }

    public List<User> listUserFromDatabase(String firstName, int userId) {

        List<User> responseUser = new ArrayList<>();
        String sql;

        if(firstName == null && userId == 0 ) {
            sql="select * from vgp_users";
        } else if (userId > 0){
            sql=String.format("select * from vgp_users where id = %d", userId);
        } else {
            sql=String.format("select * from vgp_users where first_name = %d", firstName);
        }

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

            responseUser.add(user1);
        }

        return responseUser;
    }
}
