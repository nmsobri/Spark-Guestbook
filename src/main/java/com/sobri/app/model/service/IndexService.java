package com.sobri.app.model.service;

import com.sobri.app.model.bean.PasswordBean;
import com.sobri.app.model.bean.ResetBean;
import com.sobri.app.model.repository.UserRepository;
import com.sobri.lib.Paginator;
import com.sobri.lib.Pair;
import org.eclipse.jetty.server.Authentication;
import org.mindrot.jbcrypt.BCrypt;
import spark.Request;
import com.sobri.lib.AppService;
import com.google.inject.Inject;
import com.sobri.app.model.bean.LoginBean;
import com.sobri.app.model.bean.RegisterBean;
import com.sobri.app.model.repository.IndexRepository;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class IndexService extends AppService {
    private IndexRepository indexRepository;
    private UserRepository userRepository;

    @Inject
    public IndexService(IndexRepository indexRepository, UserRepository userRepository) {
        this.indexRepository = indexRepository;
        this.userRepository = userRepository;
    }

    public List<String> Users() {
        List<String> users = new ArrayList<>();
        try {
            users = this.indexRepository.Users();
        } catch (Exception e) {
            return users;
        }

        return users;
    }

    public Pair<Boolean, String> LoginPost(Request req) {
        LoginBean userLoginBean = new LoginBean(
                req.queryParams("email"),
                req.queryParams("password")
        );

        Pair<Boolean, String> result = this.validate(userLoginBean);

        if (!result.left()) {
            return result;
        }

        try {
            Map<String, String> user = this.indexRepository.User(req.queryParams("email"));

            if (!user.isEmpty() && BCrypt.checkpw(req.queryParams("password"), user.get("password"))) {
                req.session().attribute("user", user);
                return new Pair<>(true, "Successfully login");
            } else {
                return new Pair<>(false, "Invalid username or password");
            }
        } catch (Exception e) {
            return new Pair<>(false, e.getMessage());
        }
    }

    public Pair<Boolean, String> RegisterPost(Request req) {
        RegisterBean userRegisterBean = new RegisterBean(
                req.queryParams("email"),
                req.queryParams("password"),
                req.queryParams("confirm_password"),
                req.queryParams("phone_number")
        );

        Pair<Boolean, String> result = this.validate(userRegisterBean);

        if (!result.left()) {
            return result;
        }

        try {
            if (this.indexRepository.UserIsExist(userRegisterBean.email)) {
                return new Pair<>(false, "User already exist");
            }

            if (!this.indexRepository.UserRegister(userRegisterBean)) {
                return new Pair<>(false, "Something went wrong when inserting data to database");
            }
        } catch (Exception e) {
            return new Pair<>(false, e.getMessage());
        }

        return new Pair<>(true, "Successfully registered");
    }

    public Pair<Boolean, String> saveComment(Request req) {
        try {
            Map<String, String> user = req.session().attribute("user");

            int user_id = Integer.parseInt(user.get("id"));
            String content = req.queryParams("content");

            this.indexRepository.saveComment(user_id, content);
        } catch (Exception e) {
            return new Pair<>(false, e.getMessage());
        }

        return new Pair<>(true, "Successfully add comment");
    }

    public Pair<Boolean, Pair<Paginator, List<Map<String, String>>>> Comments(Request req) {
        int page = 1;

        if (req.splat().length > 0) {
            page = Integer.parseInt(req.splat()[0]);
        }

        List<Map<String, String>> comments = new ArrayList<Map<String, String>>();

        try {
            int commentCount = this.indexRepository.commentCount();
            Paginator paginator = new Paginator("/", commentCount, 5, page);
            comments = this.indexRepository.Comments(paginator.page(), paginator.limit());
            return new Pair<>(true, new Pair<>(paginator, comments));
        } catch (Exception e) {
            return new Pair<>(false, new Pair<>(null, comments));
        }
    }

    public Pair<Boolean, String> forgotPost(Request req) {
        ResetBean resetBean = new ResetBean(req.queryParams("email"));

        Pair<Boolean, String> result = this.validate(resetBean);

        if (!result.left()) {
            return result;
        }

        try {
            Map<String, String> user = this.indexRepository.User(resetBean.email);
            int userID = Integer.parseInt(user.get("id"));
            String password = this.generatePassword();

            if (this.userRepository.changePassword(userID, password)) {
                return new Pair<>(true, "Successfully reset your password. Here is your password: " + password);
            }

        } catch (Exception e) {
            return new Pair<>(false, e.getMessage());
        }

        return new Pair<>(false, "Something went wrong while trying to reset your password");
    }

    private String generatePassword() {
        int passwordLength = 7;

        String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < passwordLength; i++) {
            Random random = new Random();
            int position = random.nextInt(36);
            password.append(chars.charAt(position));
        }

        return password.toString();
    }
}