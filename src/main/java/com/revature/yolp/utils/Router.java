package com.revature.yolp.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.yolp.daos.UserDAO;
import com.revature.yolp.handlers.AuthHandler;
import com.revature.yolp.handlers.UserHandler;
import com.revature.yolp.services.TokenService;
import com.revature.yolp.services.UserService;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

/* purpose of router class is to map endpoints */
public class Router {
    public static void router(Javalin app) {
        ObjectMapper mapper = new ObjectMapper();
        JwtConfig jwtConfig = new JwtConfig();
        TokenService tokenService = new TokenService(jwtConfig);

        /* User */
        UserDAO userDAO = new UserDAO();
        UserService userService = new UserService(userDAO);
        UserHandler userHandler = new UserHandler(userService, tokenService, mapper);

        /* auth */
        AuthHandler authHandler = new AuthHandler(userService, tokenService, mapper);

        /* Restaurant */

        /* Review */

        /* handler groups */
        /* routes -> handler -> service -> dao */
        app.routes(() -> {
            /* user */
            path("/users", () -> {
                get(userHandler::getAllUsers);
                get("/name", userHandler::getAllUsersByUsername);
                post(c -> userHandler.signup(c));
            });

            /* auth */
            path("/auth", () -> {
                post(authHandler::authenticateUser);
            });
        });
    }
}
