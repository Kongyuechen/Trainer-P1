package com.revature.yolp.services;

import com.revature.yolp.daos.UserDAO;
import com.revature.yolp.dtos.NewUserRequest;
import com.revature.yolp.models.Roles;
import com.revature.yolp.models.User;
import com.revature.yolp.utils.custom_exceptions.InvalidUserException;

import java.util.List;
import java.util.UUID;

public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void signup(NewUserRequest req) {
        List<String> usernames = userDAO.getAllUsernames();

        if (usernames.contains(req.getUsername())) {
            throw new InvalidUserException("Username is already taken");
        }
        
        if (!isValidUsername(req.getUsername())) {
            throw new InvalidUserException("Username needs to be 8-20 characters long.");
        }

        if (!isValidPassword(req.getPassword1())) {
            throw new InvalidUserException("Password needs to be minimum eight characters, at least one letter and one number");
        }

        if (!req.getPassword1().equals(req.getPassword2())) {
            throw new InvalidUserException("Password does not match");
        }

        User createdUser = new User(UUID.randomUUID().toString(), req.getUsername(), req.getPassword1(), Roles.DEFAULT);
        userDAO.save(createdUser);
    }

    public List<User> findAllUsers() {
        return userDAO.findAll();
    }

    private boolean isValidUsername(String username) {
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    }

    private boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }
}
