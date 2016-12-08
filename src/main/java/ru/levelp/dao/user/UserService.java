package ru.levelp.dao.user;

import ru.levelp.dao.BaseService;
import ru.levelp.entities.User;

import java.util.List;

public class UserService extends BaseService<User> implements UserDAO {

    public UserService() {
        super(User.class);
    }

    public List<User> getAll() {
        return request().createQuery(User.class)
                .order("name")
                .asList();
    }

    public List<User> get(List<String> ids) {
        return request().createQuery(User.class)
                .field("id").in(ids)
                .order("name")
                .asList();
    }

    public User getByEmail(String email) {
        return request().createQuery(User.class)
                .field("email").equal(email)
                .get();
    }
}
