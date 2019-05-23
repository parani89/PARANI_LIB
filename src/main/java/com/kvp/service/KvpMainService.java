package com.kvp.service;

import com.kvp.cache.GlobalCacheManager;
import com.kvp.cache.GlobalCacheManagerImpl;
import com.kvp.engine.MainService;
import com.kvp.web.controller.KvpController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("KvpMainService")
public class KvpMainService implements MainService {

    private static final Logger LOGGER = LogManager.getLogger(KvpController.class);

    @Autowired
    private GlobalCacheManager globalCacheManager;

    @Autowired
    private GlobalCacheManagerImpl globalCacheManagerImpl;

    @Override
    public void init() {
        LOGGER.info("Inside Initialization");
    }

    @Override
    public void run() {
        LOGGER.info("Inside Run");
        buildall();
    }

    private void buildall() {
        globalCacheManagerImpl.loadUserFromDatabase();
        globalCacheManagerImpl.loadBookInfoFromDatabase();
    }
}
