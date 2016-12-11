package ru.levelp;

import com.google.gson.Gson;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.levelp.api.TokenValidator;
import ru.levelp.api.WSHandler;
import ru.levelp.controllers.UserController;
import ru.levelp.dao.user.UserDAO;
import ru.levelp.dao.user.UserService;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        WSHandler wsHandler = (WSHandler) context.getBean("wsHandler");
        Gson gson = (Gson) context.getBean("gson");
        wsHandler.onRequestReceived("klfshgkldlfhgk");
    }
}
