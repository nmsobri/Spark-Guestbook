package com.sobri.app.model.entity;

import com.mysql.cj.protocol.Resultset;
import com.sobri.lib.AppEntity;
import com.sobri.sys.boot.App;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserEntity extends AppEntity {
    public String Index() {
        return "Hello Index Route";
    }

    public List<String> Users() throws Exception {
        List<String> users = new ArrayList<>();

        String query = "SELECT * FROM users";
        Statement stmt = AppEntity.connection.createStatement();
        ResultSet rset = stmt.executeQuery(query);

        while (rset.next()) {
            users.add(rset.getString("email"));
        }

        return users;
    }

    public void UserRegister() throws Exception {
        String query = "INSERT INTO users (email, password, phone) VALUES ('mail.com', 'password', '123456789')";
        Statement stmt = AppEntity.connection.createStatement();
        ResultSet rset = stmt.executeQuery(query);
    }
}