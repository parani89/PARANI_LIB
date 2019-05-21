package com.kvp.cache;

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
}
