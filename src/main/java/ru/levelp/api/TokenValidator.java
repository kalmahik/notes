package ru.levelp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levelp.dao.user.UserDAO;
import ru.levelp.entities.User;

@Component("tokenValidator")
public class TokenValidator {
    public static final String[] METHODS_TOKEN_NOT_REQUIRED = new String[] {
            Method.AUTHORIZE,
            Method.REGISTRATION
    };
    private UserDAO userService;

    @Autowired
    public TokenValidator(UserDAO userService) {
        this.userService = userService;
    }

    //TODO: return userId
    public String validate(String method, String token) throws Exception {
        for (String m : METHODS_TOKEN_NOT_REQUIRED) {
            if (method.equals(m)) {
                return null;
            }
        }
        if (token != null) {
            User user = userService.getByToken(token);
            if (user != null) {
                return user.getId();
            }
        }
        throw new Exception("token validation error");
    }
}
