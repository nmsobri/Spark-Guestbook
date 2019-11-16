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

    public Object Index(Request req, Response res) throws Exception {
        this.set("message", this.indexService.Index());
        return this.render("home/index.twig");
    }

    public Object Login(Request req, Response res) throws Exception {
        this.set("message", this.indexService.Index());
        return this.render("home/login.twig");
    }

    public Object Register(Request req, Response res) throws Exception {
        this.set("message", this.indexService.Index());
        return this.render("home/register.twig");
    }
}