package ru.levelp.controllers;

import ru.levelp.dao.user.UserDAO;
import ru.levelp.dao.user.UserService;
import ru.levelp.entities.User;

public class UserController {
    private UserDAO userService;

    public UserController(UserDAO userService) {
        this.userService = userService;
    }

    public void authorize(String email, String pwdHash) {
        User user = userService.getByEmail(email);
        if (user != null && user.getPwdHash().equals(pwdHash)) {
            //ok
        } else {
            //fail
        }
    }

}
