package com.kvp.utils;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

@Service
public class CreateDatabaseConnection {


    // Use property file for credentials
    public Connection createDatabaseTemplate() {

        String url = "jdbc:oracle:thin:@localhost:1521:ORCL";
        Properties props = new Properties();

        props.setProperty("user", "system");
        props.setProperty("password", "erode");

        Connection connection = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/ORCL", "system", "erode");

        } catch (Exception e) {
            System.out.println(e);
        }

            return connection;
    }
}
