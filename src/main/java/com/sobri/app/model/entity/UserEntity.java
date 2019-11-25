package com.sobri.app.model.entity;

import com.google.inject.Inject;
import com.sobri.lib.AppEntity;
import com.sobri.app.model.bean.RegisterBean;
import io.github.cdimascio.dotenv.Dotenv;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserEntity extends AppEntity {

    @Inject
    public UserEntity(Dotenv dotenv) {
        super(dotenv);
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
                userRegisterBean.getEmail(),
                BCrypt.hashpw(userRegisterBean.getPassword(), BCrypt.gensalt()),
                userRegisterBean.getPhoneNumber()
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

    public boolean saveComment(int user_id, String content) throws Exception {
        String query = String.format("INSERT INTO comments (email_id, comment) VALUES('%d', '%s')", user_id, content);

        Statement stmt = AppEntity.connection.createStatement();
        return !stmt.execute(query);
    }

    public List<Map<String, String>> Comments(int start, int limit) throws Exception {
        List<Map<String, String>> comments = new ArrayList<>();

        String query = String.format(
                "SELECT c.*, u.email FROM comments c INNER JOIN users u ON c.email_id = u.id ORDER by c.id ASC LIMIT %d, %d ",
                start, limit
        );

        Statement stmt = AppEntity.connection.createStatement();
        ResultSet rset = stmt.executeQuery(query);

        while (rset.next()) {
            Map<String, String> comment = new HashMap<>();
            comment.put("comment", rset.getString("comment"));
            comment.put("email", rset.getString("email"));
            comments.add(comment);
        }

        return comments;
    }


    public int commentCount() throws Exception {
        String query = "SELECT COUNT(*) total FROM comments";
        Statement stmt = AppEntity.connection.createStatement();
        ResultSet rset = stmt.executeQuery(query);

        int total = 0;
        while (rset.next()) {
            total = Integer.parseInt(rset.getString("total"));
        }

        return total;
    }

    public boolean changePassword(int userID, String password) throws Exception {
        String query = String.format("UPDATE users SET password='%s' WHERE id='%d'", BCrypt.hashpw(password, BCrypt.gensalt()), userID);

        Statement stmt = AppEntity.connection.createStatement();
        return !stmt.execute(query);
    }
}