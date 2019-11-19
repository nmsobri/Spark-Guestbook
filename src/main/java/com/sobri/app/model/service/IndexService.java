package com.sobri.app.model.service;

import spark.Request;
import com.sobri.lib.AppService;
import com.google.inject.Inject;
import com.sobri.app.model.bean.LoginBean;
import com.sobri.app.model.bean.RegisterBean;
import com.sobri.app.model.repository.IndexRepository;

import java.util.List;

public class IndexService extends AppService {
    private IndexRepository indexRepository;

    @Inject
    public IndexService(IndexRepository indexRepository) {
        this.indexRepository = indexRepository;
    }

    public List<String> Index() {
        return this.indexRepository.Index();
    }

    public boolean LoginPost(Request req) {
        LoginBean userLoginBean = new LoginBean(
                req.queryParams("email"),
                req.queryParams("password")
        );

        return this.validate(userLoginBean);
    }

    public boolean RegisterPost(Request req) {
        RegisterBean userRegisterBean = new RegisterBean(
                req.queryParams("email"),
                req.queryParams("password"),
                req.queryParams("anything"),
                req.queryParams("anything")
        );

        return this.validate(userRegisterBean);
    }
}