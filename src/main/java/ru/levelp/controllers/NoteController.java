package ru.levelp.controllers;

import ru.levelp.api.entities.ResponseContainer;
import ru.levelp.dao.note.NoteDAO;
import ru.levelp.entities.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller("noteController")
public class NoteController {
    private NoteDAO noteService;

    @Autowired
    public NoteController(NoteDAO noteService) {
        this.noteService = noteService;
    }

    public ResponseContainer<Note> createNote(String title, String body, String author) throws Exception {
        if (title != null && !title.isEmpty() && body != null && !body.isEmpty()) {
            Note note = new Note();
            note.setId(UUID.randomUUID().toString());
            note.setAuthor(author);
            note.setTitle(title);
            note.setBody(body);
            note.setCreated(new Date().getTime());
            note.setUpdated(note.getCreated());
            noteService.add(note);
            return new ResponseContainer<>(note);
        }
        throw new Exception("create note error");
    }

    public ResponseContainer<Note> editNote(String id, String title, String body) throws Exception {
        if (id != null && title != null && body != null && !title.isEmpty() && !body.isEmpty()) {
            Note note = noteService.get(id);
            if (note != null) {
                note.setTitle(title);
                note.setBody(body);
                note.setUpdated(new Date().getTime());
                noteService.update(note);
                return new ResponseContainer<>(note);
            }
        }
        throw new Exception("edit note error");
    }

    public ResponseContainer<Note> deleteNote(String id) throws Exception {
        if (id != null) {
            Note note = noteService.get(id);
            if (note != null) {
                noteService.delete(note);
                return new ResponseContainer<>(note);
            }
        }
        throw new Exception("delete note error");
    }

    public ResponseContainer<List<Note>> getNotes(String userId) {
        return new ResponseContainer<>(noteService.getNotesForUser(userId));
    }

    public ResponseContainer<Boolean> addAccessRight(String noteId, String userEmail, int mode) throws Exception {
        throw new Exception("unsupported");
    }

    public ResponseContainer<Boolean> removeAccessRight(String noteId, String userEmail) throws Exception {
        throw new Exception("unsupported");
    }
}
