package ru.levelp.dao;

import ru.levelp.entities.BaseEntity;

public interface BaseDAO<E extends BaseEntity> {

    void add(E note);

    void delete(E note);

    E get(String id);

    void update(E note);

}
