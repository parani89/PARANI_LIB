package com.kvp.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class CreateConnection {

    @Autowired
    private DataSource dataSource;

    @Bean("datasource")
    public DataSource createDataSource() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername("system");
        dataSource.setPassword("Erode123");
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521/orclpdb");
        return dataSource;
    }


    @Bean(name = "MyJdbcTempate")
    public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}