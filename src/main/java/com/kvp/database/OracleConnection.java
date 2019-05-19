package com.kvp.database;

import java.sql.*;
class OracleCon{
    public static void main(String args[]){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/ORCL","system","erode");

            //Connection con=DriverManager.getConnection(
             //      "jdbc:oracle:thin:system/erode@//localhost:1521/orcl");
            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("select * from emp2");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));

            con.close();

        }catch(Exception e){ System.out.println(e);}

    }
}