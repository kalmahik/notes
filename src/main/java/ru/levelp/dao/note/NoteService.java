package ru.levelp.dao.note;

import org.mongodb.morphia.query.Query;
import ru.levelp.dao.BaseService;
import ru.levelp.entities.Note;

import java.util.List;

public class NoteService extends BaseService<Note> implements NoteDAO {

    public NoteService() {
        super(Note.class);
    }

    public List<Note> getForUser(String id) {
         Query<Note> query = request().createQuery(Note.class);
         query.or(
                 query.criteria("author").equal(id),
                 query.criteria("accessRights.userId").equal(id)
         );
         return query
                 .order("updated")
                 .asList();
    }
}
