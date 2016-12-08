package ru.levelp.dao.user;

import ru.levelp.dao.BaseDAO;
import ru.levelp.entities.User;

import java.util.List;

public interface UserDAO extends BaseDAO<User> {

    List<User> getAll();

    List<User> get(List<String> ids);

    User getByEmail(String email);

}
