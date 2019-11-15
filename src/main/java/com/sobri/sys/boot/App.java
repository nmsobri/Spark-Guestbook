package com.sobri.sys.boot;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.AbstractModule;
import io.github.cdimascio.dotenv.Dotenv;

import static spark.Spark.*;

public class App {

    public void start() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(Dotenv.class).toInstance(Dotenv.load());
            }
        });

        Dotenv dotenv = injector.getInstance(Dotenv.class);

        String port = dotenv.get("port");
        int portNumber = (port != null) ? Integer.parseInt(port) : 8080;

        port(portNumber);
        new Router(injector).register();
    }
}