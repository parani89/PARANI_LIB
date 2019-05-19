package com.kvp.engine;

import com.kvp.utils.DatabaseAccessTxnManager;
import com.kvp.web.controller.KvpController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = "com.kvp")
public class KvpServer {

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
        SpringApplication.run(KvpServer.class,args);
    }
}