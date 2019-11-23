package com.sobri.app.controller;

import com.sobri.lib.Pair;
import spark.Request;
import spark.Response;
import com.google.inject.Inject;
import com.sobri.lib.AppController;
import com.sobri.app.model.service.UserService;

import java.util.Map;

public class UserController extends AppController {
    private UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public Object ProfileGet(Request req, Response res) throws Exception {
        Map<String, String> userSession = req.session().attribute("user");
        Pair<Boolean, Map<String, String>> result = this.userService.User(userSession.get("email"));

        if (result.left()) {
            System.out.println(result.right());
            this.set("user", result.right());
        }

        return this.render(req, "user/profile.twig");
    }

    public Object ProfilePost(Request req, Response res) throws Exception {
        Pair<Boolean, String> result = this.userService.changePassword(req);

        if (result.left()) {
            req.session().attribute("flash_success", result.right());
            res.redirect("/profile");
        } else {
            req.session().attribute("flash_error", result.right());
            res.redirect("/profile");
        }

        return null;
    }
}
