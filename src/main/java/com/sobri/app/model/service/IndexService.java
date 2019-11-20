package com.sobri.app.model.service;

import com.sobri.lib.Pair;
import spark.Request;
import com.sobri.lib.AppService;
import com.google.inject.Inject;
import com.sobri.app.model.bean.LoginBean;
import com.sobri.app.model.bean.RegisterBean;
import com.sobri.app.model.repository.IndexRepository;

import java.util.List;
import java.util.ArrayList;

public class IndexService extends AppService {
    private IndexRepository indexRepository;

    @Inject
    public IndexService(IndexRepository indexRepository) {
        this.indexRepository = indexRepository;
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

        return this.validate(userLoginBean);
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
}