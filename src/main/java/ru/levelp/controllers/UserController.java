package ru.levelp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.levelp.api.entities.ResponseContainer;
import ru.levelp.dao.user.UserDAO;
import ru.levelp.entities.User;

import java.util.List;
import java.util.UUID;

@Controller("userController")
public class UserController {
    private UserDAO userService;

    @Autowired
    public UserController(UserDAO userService) {
        this.userService = userService;
    }

    public ResponseContainer<String> authorize(String email, String pwdHash) throws Exception {
        User user = userService.getByEmail(email);
        if (user != null && user.getPwdHash().equals(pwdHash)) {
            user.setToken(UUID.randomUUID().toString() + UUID.randomUUID().toString());
            userService.update(user);
            return new ResponseContainer<>(user.getToken());
        }
        throw new Exception("auth error");
    }

    public ResponseContainer<String> registration(String email, String pwdHash, String name) throws Exception {
        User user = userService.getByEmail(email);
        if (user == null && email != null && pwdHash != null && name != null) {
            if (!email.isEmpty() && !pwdHash.isEmpty() && !name.isEmpty()) {
                user = new User();
                user.setId(UUID.randomUUID().toString());
                user.setEmail(email);
                user.setToken(UUID.randomUUID().toString() + UUID.randomUUID().toString());
                user.setPwdHash(pwdHash);
                user.setName(name);
                userService.add(user);
                return new ResponseContainer<>(user.getToken());
            }
        }
        throw new Exception("register error");
    }

    public ResponseContainer<List<User>> getUsers() {
        return new ResponseContainer<>(userService.getAll());
    }

    public ResponseContainer<User> getUser(String id) {
        return new ResponseContainer<>(userService.get(id));
    }

}
