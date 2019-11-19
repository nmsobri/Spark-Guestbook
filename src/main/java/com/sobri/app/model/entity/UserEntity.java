package com.sobri.app.model.entity;

import com.sobri.lib.AppEntity;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserEntity extends AppEntity {
    public String Index() {
        return "Hello Index Route";
    }

    public List<String> Users() {
        List<String> users = new ArrayList<>();

        String queryString = "SELECT * FROM users";
        try {
            Statement stmt = AppEntity.connection.createStatement();
            ResultSet rset = stmt.executeQuery(queryString);

            while (rset.next()) {
                users.add(rset.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }
}