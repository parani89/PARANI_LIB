package com.kvp.engine;

import com.kvp.cache.GlobalCacheManager;
import com.kvp.cache.GlobalCacheManagerImpl;
import com.kvp.service.KvpMainService;
import com.kvp.web.controller.KvpController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = "com.kvp")
public class KvpServer {

    @Autowired
    private GlobalCacheManagerImpl globalCacheManagerImpl;

    public static void main(String[] args) {
        new KvpServer().run(args);
    }

    public void run(String[] args) {
        logSysProperties();
        runServer(args);
    }

    private void logSysProperties() {

    }

    private void runServer(String[] args) {
        runSprigContainer(args);
    }

    private void runSprigContainer(String[] args) {

        ConfigurableApplicationContext springContainer = SpringApplication.run(KvpConfiguration.class, args);

        final Logger LOGGER = LogManager.getLogger(KvpController.class);

        LOGGER.info("Running Main application");

        MainService mainService = (MainService) springContainer.getBean(KvpMainService.class);
        mainService.init();
        mainService.run();
    }

}