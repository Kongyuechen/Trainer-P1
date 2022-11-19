package com.revature.yolp.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.yolp.dtos.NewUserRequest;
import com.revature.yolp.models.User;
import com.revature.yolp.services.UserService;
import com.revature.yolp.utils.custom_exceptions.InvalidUserException;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/* User controller */
public class UserHandler {
    private final UserService userService;
    private final ObjectMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(User.class);

    public UserHandler(UserService userService, ObjectMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    /* return all users */
    public void getAllUsers(Context ctx) {
        ctx.json(userService.findAllUsers());
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
