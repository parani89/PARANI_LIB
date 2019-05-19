package com.kvp.utils;

import java.sql.Connection;

public class DatabaseAccessTxnManager {

    public Connection getDatabaseTemplateWithNoOption() {

        CreateDatabaseConnection createDatabaseConnection = new CreateDatabaseConnection();
        Connection connection = createDatabaseConnection.createDatabaseTemplate();
        return connection;
    }
}

