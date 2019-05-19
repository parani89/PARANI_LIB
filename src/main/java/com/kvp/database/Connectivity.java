package com.kvp.database;


import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.util.Properties;

public class Connectivity {
    public static void main(String args[]) throws SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521:ORCL";
        Properties props = new Properties();

        props.setProperty("user", "system");
        props.setProperty("password", "erode");

        try {
            Class.forName ("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        Connection conn = DriverManager.getConnection(url,props);
        String sql ="select * from emp2";

        PreparedStatement preStatement = conn.prepareStatement(sql);

        ResultSet result = preStatement.executeQuery();

        while(result.next()){
            System.out.println("Current Date from Oracle : " +  result.getString("name"));
        }

        System.out.println("done");

    }
}