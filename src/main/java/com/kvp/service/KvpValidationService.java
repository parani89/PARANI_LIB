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

    public boolean validateBookExistance(int bookId) {

        boolean retValue = false;

        if(globalCacheManager.getBookIdMap() !=null) {
            if (globalCacheManager.getBookIdMap().containsKey(bookId)) {
                retValue=true;
            }
        } else {
            retValue=false;
        }

        return retValue;
    }

    public boolean IsUserHoldingBooks(int id, String type) {

        if(type.equals("user")) {
            if (globalCacheManager.getUserIdMap().get(id).getBooksAvailed() > 0) {
                return true;
            } else {
                return false;
            }
        } else if(type.equals("book")) {
            if (globalCacheManager.getBookIdMap().get(id).getAvailability().equals("Y")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean isBookAlive(int bookId) {

        return globalCacheManager.getBookIdMap().get(bookId).getAlive().equals("Y");
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
