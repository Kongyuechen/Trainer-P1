package com.revature.yolp.services;

import com.revature.yolp.daos.UserDAO;

/* purpose of UserService is to validate and retrieve data from the DAO (DATA ACCESS OBJECT) */
/* Service class is essentially an api */
public class UserService {
    /* dependency injection = when a class is dependent on another class */
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
