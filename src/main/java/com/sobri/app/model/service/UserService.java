package com.sobri.app.model.service;

import com.google.inject.Inject;
import com.sobri.app.model.bean.PasswordBean;
import com.sobri.app.model.repository.IndexRepository;
import com.sobri.app.model.repository.UserRepository;
import com.sobri.lib.AppService;
import com.sobri.lib.Pair;
import spark.Request;

import java.util.Map;

public class UserService extends AppService {
    private UserRepository userRepository;

    @Inject
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Pair<Boolean, Map<String, String>> User(String email) {
        try {
            Map<String, String> user = this.userRepository.User(email);
            return new Pair<>(true, user);
        } catch (Exception e) {
            return new Pair<>(false, null);
        }
    }

    public Pair<Boolean, String> changePassword(Request req) {
        String password = req.queryParams("password");
        String confirmPassword = req.queryParams("confirm_password");

        PasswordBean passwordBean = new PasswordBean(password, confirmPassword);

        Pair<Boolean, String> result = this.validate(passwordBean);

        if (!result.left()) {
            return result;
        }

        Map<String, String> user = req.session().attribute("user");
        int userID = Integer.parseInt(user.get("id"));

        try {
            this.userRepository.changePassword(userID, password);
            return new Pair<>(true, "Successfully change password");
        } catch (Exception e) {
            return new Pair<>(false, e.getMessage());
        }
    }
}
