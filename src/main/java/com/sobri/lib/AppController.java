package com.sobri.lib;

import spark.ModelAndView;
import spark.template.jtwig.JtwigTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class AppController {
    private static Map<String, Object> data = new HashMap<>();

    public void set(String key, Object val) {
        AppController.data.put(key, val);
    }

    public Object render(String tplName) {
        ModelAndView modelView = new ModelAndView(AppController.data, tplName);
        JtwigTemplateEngine template = new JtwigTemplateEngine();
        return template.render(modelView);
    }
}