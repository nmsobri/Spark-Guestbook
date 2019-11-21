package com.sobri.app.model.entity;

import com.sobri.lib.AppEntity;
import com.sobri.app.model.bean.RegisterBean;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String, String> User(String email) throws Exception {
        Map<String, String> user = new HashMap<>();

        String query = String.format("SELECT * FROM users WHERE email='%s'", email);
        Statement stmt = AppEntity.connection.createStatement();
        ResultSet rset = stmt.executeQuery(query);

        while (rset.next()) {
            user.put("id", rset.getString("id"));
            user.put("email", rset.getString("email"));
            user.put("password", rset.getString("password"));
            user.put("phone", rset.getString("phone"));
        }

        return user;
    }

    public boolean UserRegister(RegisterBean userRegisterBean) throws Exception {
        String query = String.format(
                "INSERT INTO users (email, password, phone) VALUES ('%s', '%s', '%s')",
                userRegisterBean.email,
                BCrypt.hashpw(userRegisterBean.password, BCrypt.gensalt()),
                userRegisterBean.phoneNumber
        );

        Statement stmt = AppEntity.connection.createStatement();
        return !stmt.execute(query);
    }

    public boolean UserIsExist(String email) throws Exception {
        String query = String.format("SELECT * FROM users WHERE email ='%s'", email);
        Statement stmt = AppEntity.connection.createStatement();

        ResultSet rset = stmt.executeQuery(query);
        return rset.next();
    }
}