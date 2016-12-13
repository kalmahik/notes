package ru.levelp.dao.note;

import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Service;
import ru.levelp.dao.BaseService;
import ru.levelp.entities.Note;

import java.util.List;

@Service("noteService")
public class NoteService extends BaseService<Note, String> implements NoteDAO {

    public NoteService() {
        super(Note.class);
    }

    @Override
    public List<Note> getNotesForUser(String id) {
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
