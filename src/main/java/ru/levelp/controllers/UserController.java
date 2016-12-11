package ru.levelp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.levelp.api.entities.ResponseContainer;
import ru.levelp.dao.user.UserDAO;
import ru.levelp.entities.User;

import java.util.UUID;

@Controller("userController")
public class UserController {
    private UserDAO userService;

    @Autowired
    public UserController(UserDAO userService) {
        this.userService = userService;
    }

    public ResponseContainer<String> authorize(String email, String pwdHash) {
        User user = userService.getByEmail(email);
        if (user != null && user.getPwdHash().equals(pwdHash)) {
            user.setToken(UUID.randomUUID().toString() + UUID.randomUUID().toString());
            userService.update(user);
            ResponseContainer<String> responseContainer = new ResponseContainer<>();
            responseContainer.setPayload(user.getToken());
            return responseContainer;
        }
        throw new InternalError();
    }

}
