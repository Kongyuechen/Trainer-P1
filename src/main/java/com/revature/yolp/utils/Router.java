package com.revature.yolp.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.yolp.handlers.UserHandler;
import com.revature.yolp.daos.UserDAO;
import com.revature.yolp.services.UserService;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Router {
    public static void router(Javalin app) {
        ObjectMapper mapper = new ObjectMapper();

        /* users -> dependency injection: when a class depends on another class */
        UserDAO userDAO = new UserDAO();
        UserService userService = new UserService(userDAO);
        UserHandler userHandler = new UserHandler(userService, mapper);

        /* restaurants */


        /* reviews */

        /* handler groups */
        app.routes(() -> {
            /* users path */
            path("/users", () -> {
                get(userHandler::getAllUsers);
                post(userHandler::saveUser);
            });
            path("/users/name", () -> {
                get(userHandler::getUsersByName);
            });

            /* restaurants path */
            path("restaurants", () -> {
                get(userHandler::getAllUsers);
            });

            /* reviews path */
        });
    }
}
