package ru.levelp.api.entities;

public class EditNotePayload {
    private String noteId;
    private String title;
    private String body;

    public String getNoteId() {
        return noteId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
