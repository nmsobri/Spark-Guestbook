package com.sobri.lib;

import spark.Request;
import spark.ModelAndView;
import spark.template.jtwig.JtwigTemplateEngine;

import java.util.Map;
import java.util.HashMap;

public class AppController {
    private static Map<String, Object> data = new HashMap<>();

    public void set(String key, Object val) {
        AppController.data.put(key, val);
    }

    public void flashMessage(Request req) {
        this.set("flash_error", req.session().attribute("flash_error"));
        this.set("flash_success", req.session().attribute("flash_success"));

        req.session().removeAttribute("flash_error");
        req.session().removeAttribute("flash_success");
    }

    public Object render(Request req, String tplName) {
        if (req.session().attribute("user") != null) {
            this.set("logged_in", true);
        } else {
            this.set("logged_in", false);
        }

        ModelAndView modelView = new ModelAndView(AppController.data, tplName);
        JtwigTemplateEngine template = new JtwigTemplateEngine();
        return template.render(modelView);
    }
}