package ru.levelp.dao;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import ru.levelp.entities.BaseEntity;

public abstract class BaseService<E extends BaseEntity> implements BaseDAO<E> {
    private Datastore db;
    private Class<E> entityType;

    public BaseService(Class<E> entityType) {
        this.entityType = entityType;

        Morphia morphia = new Morphia();
        db = morphia.createDatastore(
                new MongoClient("localhost"), "levelupnotes");
        db.ensureIndexes();
    }

    public Datastore request() {
        return db;
    }

    public void add(E note) {
        db.save(note);
    }

    public void delete(E note) {
        db.delete(note);
    }

    public E get(String id) {
        return db.get(entityType, id);
    }

    public void update(E note) {
        add(note);
    }
}
