package com.revature.yolp.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.yolp.dtos.requests.NewUserRequest;
import com.revature.yolp.dtos.responses.Principal;
import com.revature.yolp.models.Role;
import com.revature.yolp.models.User;
import com.revature.yolp.services.TokenService;
import com.revature.yolp.services.UserService;
import com.revature.yolp.utils.custom_exceptions.InvalidAuthException;
import com.revature.yolp.utils.custom_exceptions.InvalidUserException;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/* User handler */
public class UserHandler {
    private final UserService userService;
    private final TokenService tokenService;
    private final ObjectMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(User.class);

    public UserHandler(UserService userService, TokenService tokenService, ObjectMapper mapper) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.mapper = mapper;
    }

    /* return all users */
    public void getAllUsers(Context ctx) {
        try {
            String token = ctx.req.getHeader("authorization");
            if (token == null) throw new InvalidAuthException("You are not signed in");
            Principal principal = tokenService.extractRequesterDetails(token);
            if (principal == null) throw new InvalidAuthException("You are not signed in");
            if (!principal.getRole().equals(Role.ADMIN)) throw new InvalidAuthException("You are not authorized to do this");

            ctx.json(userService.findAllUsers());
        } catch (InvalidAuthException e) {
            ctx.status(401);
            ctx.json(e);
        }
    }

    /* return user with matching names */
    public void getUsersByName(Context ctx) {
        ctx.json(userService.findAllUsersByUsername(ctx.req.getParameter("name")));
    }

    /* save user */
    public void saveUser(Context ctx) throws IOException {
        NewUserRequest userRequest = mapper.readValue(ctx.req.getInputStream(), NewUserRequest.class);
        try {
            userService.signup(userRequest);
            ctx.status(201);
        } catch (InvalidUserException e) {
            ctx.status(403);
            ctx.json(e);
            logger.info(e.getMessage());
        }
    }
}
