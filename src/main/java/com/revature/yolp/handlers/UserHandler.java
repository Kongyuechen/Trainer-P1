package com.revature.yolp.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.yolp.dtos.NewUserRequest;
import com.revature.yolp.models.User;
import com.revature.yolp.services.UserService;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/* User controller */
public class UserController {
    private final UserService userService;
    private final ObjectMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(User.class);

    public UserController(UserService userService, ObjectMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    /* return all users */
    public void getAllUser() {

    }

    /* save user */
    public Handler saveUser(Context c) throws IOException {
        NewUserRequest userReq = mapper.readValue(c.req.getInputStream(), NewUserRequest.class);
        return ctx -> {
            ctx.status(201);
            userService.signup(userReq);
        };
    }
}
