package com.sobri.app.controller;

import spark.Request;
import spark.Response;
import com.sobri.lib.Pair;
import com.google.inject.Inject;
import com.sobri.lib.AppController;
import com.sobri.app.model.service.IndexService;

import java.util.List;
import java.util.Map;

public class IndexController extends AppController {
    private IndexService indexService;

    @Inject
    public IndexController(IndexService indexService) {
        this.indexService = indexService;
    }

    public Object IndexGet(Request req, Response res) throws Exception {
        Pair<Boolean, List<Map<String, String>>> result = this.indexService.Comments();

        this.flashMessage(req);
        this.set("comments", result.right());
        return this.render(req, "home/index.twig");
    }

    public Object IndexPost(Request req, Response res) throws Exception {
        Pair<Boolean, String> result = this.indexService.saveComment(req);

        if (result.left()) {
            req.session().attribute("flash_success", result.right());
            res.redirect("/");
        } else {
            req.session().attribute("flash_error", result.right());
            res.redirect("/login");
        }

        return null;
    }

    public Object LoginGet(Request req, Response res) throws Exception {
        this.flashMessage(req);
        return this.render(req, "home/login.twig");
    }

    public Object LoginPost(Request req, Response res) throws Exception {
        Pair<Boolean, String> result = this.indexService.LoginPost(req);

        if (!result.left()) {
            req.session().attribute("flash_error", result.right());
            res.redirect("/login");
        } else {
            req.session().attribute("flash_success", result.right());
            res.redirect("/");
        }

        return null;
    }

    public Object LogoutGet(Request req, Response res) throws Exception {
        req.session().removeAttribute("user");
        req.session().attribute("flash_success", "Successfully logout");
        res.redirect("/login");
        return null;
    }

    public Object RegisterGet(Request req, Response res) throws Exception {
        this.flashMessage(req);
        return this.render(req, "home/register.twig");
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
        return this.render(req, "home/forgot.twig");
    }

    public Object ForgotPost(Request req, Response res) throws Exception {
        return this.render(req, "home/forgot.twig");
    }
}