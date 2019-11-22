package com.sobri.sys.boot;

import static spark.Spark.*;

import com.sobri.lib.AppFilter;
import com.google.inject.Injector;
import com.sobri.app.controller.IndexController;

public class Router {
    private final Injector injector;

    public Router(Injector injector) {
        this.injector = injector;
    }

    public <T> T instance(Class<T> c) {
        return this.injector.getInstance(c);
    }

    public void register() {
        // before("*", AppFilter.addTrailingSlashes);

        before("/", (req, res) -> {
            if (req.requestMethod().equals("POST")) {

                if (req.session().attribute("user") == null) {
                    req.session().attribute("flash_error", "Please login first!");
                    res.redirect("/");
                }
            }
        });

        // Add your route here
        get("/", this.instance(IndexController.class)::IndexGet);
        post("/", this.instance(IndexController.class)::IndexPost);

        get("/login", this.instance(IndexController.class)::LoginGet);
        post("/login", this.instance(IndexController.class)::LoginPost);

        get("/logout", this.instance(IndexController.class)::LogoutGet);

        get("/register", this.instance(IndexController.class)::RegisterGet);
        post("/register", this.instance(IndexController.class)::RegisterPost);

        get("/forgot", this.instance(IndexController.class)::ForgotGet);
        post("/forgot", this.instance(IndexController.class)::ForgotPost);

        after("*", AppFilter.addGzipHeader);
    }
}