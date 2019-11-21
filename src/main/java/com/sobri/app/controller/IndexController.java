package com.sobri.app.controller;

import com.sobri.lib.Pair;
import spark.Request;
import spark.Response;
import com.google.inject.Inject;
import com.sobri.lib.AppController;
import com.sobri.app.model.service.IndexService;

import java.util.List;

public class IndexController extends AppController {
    private IndexService indexService;

    @Inject
    public IndexController(IndexService indexService) {
        this.indexService = indexService;
    }

    public Object IndexGet(Request req, Response res) throws Exception {
        this.set("flash_error", req.session().attribute("flash_error"));
        this.set("flash_success", req.session().attribute("flash_success"));

        req.session().removeAttribute("flash_error");
        req.session().removeAttribute("flash_success");

        List<String> users = this.indexService.Users();
        this.set("users", users);
        return this.render("home/index.twig");
    }

    public Object IndexPost(Request req, Response res) throws Exception {
        return this.render("home/index.twig");
    }

    public Object LoginGet(Request req, Response res) throws Exception {
        this.set("flash_error", req.session().attribute("flash_error"));
        req.session().removeAttribute("flash_error");

        return this.render("home/login.twig");
    }

    public Object LoginPost(Request req, Response res) throws Exception {
        Pair<Boolean, String> result = this.indexService.LoginPost(req);

        if (!result.left()) {
            req.session().attribute("flash_error", result.right());
            res.redirect("/login");
        } else {
            res.redirect("/");
            req.session().attribute("flash_success", result.right());
        }

        return null;
    }

    public Object RegisterGet(Request req, Response res) throws Exception {
        this.set("flash_error", req.session().attribute("flash_error"));
        this.set("flash_success", req.session().attribute("flash_success"));

        req.session().removeAttribute("flash_error");
        req.session().removeAttribute("flash_success");

        return this.render("home/register.twig");
    }

    public Object RegisterPost(Request req, Response res) throws Exception {
        Pair<Boolean, String> result = this.indexService.RegisterPost(req);

        if (!result.left()) {
            req.session().attribute("flash_error", result.right());
        } else {
            req.session().attribute("flash_success", result.right());
        }

        res.redirect("/register");
        return null;
    }

    public Object ForgotGet(Request req, Response res) throws Exception {
        return this.render("home/forgot.twig");
    }

    public Object ForgotPost(Request req, Response res) throws Exception {
        return this.render("home/forgot.twig");
    }
}