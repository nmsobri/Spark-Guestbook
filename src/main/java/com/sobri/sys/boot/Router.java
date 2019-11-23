package com.sobri.sys.boot;

import static spark.Spark.*;

import com.sobri.app.controller.UserController;
import com.sobri.lib.AppFilter;
import com.google.inject.Injector;
import com.sobri.app.controller.IndexController;
import spark.Request;
import spark.Response;

import java.util.function.BiConsumer;
import java.util.function.Function;

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

        BiConsumer<Request, Response> checkLogin = (req, res) -> {
            if (req.session().attribute("user") == null) {
                req.session().attribute("flash_error", "Please login first!");
                res.redirect("/");
                halt();
            }
        };

        before("/", (req, res) -> {
            if (req.requestMethod().equals("POST")) {
                checkLogin.accept(req, res);
            }
        });

        before("/profile", checkLogin::accept);

        // Add your route here
        get("/login", this.instance(IndexController.class)::LoginGet);
        post("/login", this.instance(IndexController.class)::LoginPost);

        get("/logout", this.instance(IndexController.class)::LogoutGet);

        get("/register", this.instance(IndexController.class)::RegisterGet);
        post("/register", this.instance(IndexController.class)::RegisterPost);

        get("/forgot", this.instance(IndexController.class)::ForgotGet);
        post("/forgot", this.instance(IndexController.class)::ForgotPost);

        get("/profile", this.instance(UserController.class)::ProfileGet);
        post("/profile", this.instance(UserController.class)::ProfilePost);

        // Need to add here due to splat operator, or else it gona match all route that start with '/'
        get("/*", this.instance(IndexController.class)::IndexGet);
        post("/", this.instance(IndexController.class)::IndexPost);

        after("*", AppFilter.addGzipHeader);
    }
}