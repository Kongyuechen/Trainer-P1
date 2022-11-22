package com.revature.yolp.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.yolp.daos.UserDAO;
import com.revature.yolp.handlers.UserHandler;
import com.revature.yolp.services.UserService;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

/* purpose of router class is to map endpoints */
public class Router {
    public static void router(Javalin app) {
        ObjectMapper mapper = new ObjectMapper();

        /* User */
        UserDAO userDAO = new UserDAO();
        UserService userService = new UserService(userDAO);
        UserHandler userHandler = new UserHandler(userService, mapper);

        /* Restaurant */

        /* Review */

        /* handler groups */
        /* routes -> handler -> service -> dao */
        app.routes(() -> {
            path("/users", () -> {
                post(c -> userHandler.signup(c));
            });
        });
    }
}
