package com.sobri.lib;

import java.sql.Connection;
import java.sql.DriverManager;

public class AppEntity {
    protected static Connection connection;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/guestbook";
            AppEntity.connection = DriverManager.getConnection(url, "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}