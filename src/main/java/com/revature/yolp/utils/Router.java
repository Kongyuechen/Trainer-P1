package com.revature.yolp.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.yolp.handlers.AuthHandler;
import com.revature.yolp.handlers.UserHandler;
import com.revature.yolp.daos.UserDAO;
import com.revature.yolp.services.TokenService;
import com.revature.yolp.services.UserService;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Router {
    public static void router(Javalin app) {
        ObjectMapper mapper = new ObjectMapper();
        JwtConfig jwtConfig = new JwtConfig();
        TokenService tokenService = new TokenService(jwtConfig);

        /* users -> dependency injection: when a class depends on another class */
        UserDAO userDAO = new UserDAO();
        UserService userService = new UserService(userDAO);
        UserHandler userHandler = new UserHandler(userService, tokenService, mapper);

        /* auth */
        AuthHandler authHandler = new AuthHandler(userService, tokenService, mapper);

        /* restaurants */


        /* reviews */

        /* handler groups */
        app.routes(() -> {
            /* users path */
            path("/users", () -> {
                get(userHandler::getAllUsers);
                get("/name", userHandler::getUsersByName);
                post("/signup", userHandler::saveUser);
            });

            /* Auth */
            path("/auth", () -> {
                post(authHandler::authenticate);
            });

            /* restaurants path */
            path("restaurants", () -> {
                get(userHandler::getAllUsers);
            });

            /* reviews path */
        });
    }
}
