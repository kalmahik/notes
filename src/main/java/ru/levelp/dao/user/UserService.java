package ru.levelp.dao.user;

import org.springframework.stereotype.Service;
import ru.levelp.dao.BaseService;
import ru.levelp.entities.User;

import java.util.List;

@Service("userService")
public class UserService extends BaseService<User, String> implements UserDAO {

    public UserService() {
        super(User.class);
    }

    @Override
    public List<User> getAll() {
        return request().createQuery(User.class)
                .order("name")
                .asList();
    }

    @Override
    public List<User> get(List<String> ids) {
        return request().createQuery(User.class)
                .field("id").in(ids)
                .order("name")
                .asList();
    }

    @Override
    public User getByEmail(String email) {
        return request().createQuery(User.class)
                .field("email").equal(email)
                .get();
    }

    @Override
    public User getByToken(String token) {
        return request().createQuery(User.class)
                .field("token").equal(token)
                .get();
    }
}
