package com.sobri.app.controller;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import com.google.inject.Inject;
import com.sobri.lib.AppController;
import com.sobri.app.model.service.IndexService;

import java.util.HashMap;
import java.util.Map;

public class IndexController extends AppController {
    private IndexService indexService;

    @Inject
    public IndexController(IndexService indexService) {
        this.indexService = indexService;
    }

    public Object IndexGet(Request req, Response res) throws Exception {
        return this.render("home/index.twig");
    }

    public Object IndexPost(Request req, Response res) throws Exception {
        return this.render("home/index.twig");
    }

    public Object LoginGet(Request req, Response res) throws Exception {
        this.set("error", req.session().attribute("flash"));
        req.session().removeAttribute("flash");
        return this.render("home/login.twig");
    }

    public Object LoginPost(Request req, Response res) throws Exception {
        if (!this.indexService.LoginPost(req)) {
            req.session().attribute("flash", this.indexService.getMessage());
            res.redirect("/login");
        } else {
            res.redirect("/");
            req.session().attribute("flash", "Successfully login!");
        }

        return null;
    }

    public Object RegisterGet(Request req, Response res) throws Exception {
        this.set("error", req.session().attribute("flash"));
        req.session().removeAttribute("flash");
        return this.render("home/register.twig");
    }

    public Object RegisterPost(Request req, Response res) throws Exception {
        if (!this.indexService.RegisterPost(req)) {
            req.session().attribute("flash", this.indexService.getMessage());
        } else {
            req.session().attribute("flash", "Successfully register!");
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