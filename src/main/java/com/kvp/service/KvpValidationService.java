package com.kvp.service;

import com.kvp.cache.GlobalCacheManager;
import com.kvp.web.domain.BookMaster;
import com.kvp.web.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KvpValidationService {

    @Autowired
    private GlobalCacheManager globalCacheManager;

    public boolean validateUserExistance(User user) {

        boolean retValue = false;

        if(globalCacheManager.getUserIdMap() !=null) {
            if (globalCacheManager.getUserIdMap().containsKey(user.getId())) {
                retValue=true;
            }
        } else {
            retValue=false;
        }

        return retValue;
    }

    public boolean validateBookMasterAvailability(BookMaster bookMaster) {

        boolean retValue = false;

        if(globalCacheManager.getBookMasterMap() != null) {
            if (globalCacheManager.getBookMasterMap().containsKey(bookMaster.getBookGroupId())) {
                retValue=true;
            }
        } else {
            retValue=false;
        }

        return retValue;
    }
}
