package com.sobri.lib;

import com.google.inject.Inject;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;

public class AppEntity {
    protected Dotenv dotenv;
    protected static Connection connection;

    public AppEntity(Dotenv dotenv) {
        this.dotenv = dotenv;

        try {
            String dbName = this.dotenv.get("dbName");
            String dbServer = this.dotenv.get("dbServer");
            String dbUser = this.dotenv.get("dbUser");
            String dbPass = this.dotenv.get("dbPass");

            Class.forName("com.mysql.jdbc.Driver");
            String url = String.format("jdbc:mysql://%s/%s", dbServer, dbName);
            AppEntity.connection = DriverManager.getConnection(url, dbUser, dbPass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}