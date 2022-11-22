package com.revature.yolp.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.yolp.dtos.requests.NewUserRequest;
import com.revature.yolp.services.UserService;
import com.revature.yolp.utils.custom_exceptions.InvalidUserException;
import io.javalin.http.Context;

import java.io.IOException;

/* purpose of this UserHandler class is to handle http verbs and endpoints */
/* hierarchy dependency injection -> userhandler -> userservice -> userdao */
public class UserHandler {
    private final UserService userService;
    private final ObjectMapper mapper;

    public UserHandler(UserService userService, ObjectMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    public void signup(Context c) throws IOException {
        NewUserRequest req = mapper.readValue(c.req.getInputStream(), NewUserRequest.class);
        try {
            userService.saveUser(req);
            c.status(201); // CREATED
        } catch (InvalidUserException e) {
            c.status(403); // FORBIDDEN
            c.json(e);
        }
    }
}
