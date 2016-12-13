package ru.levelp.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.levelp.api.entities.*;
import ru.levelp.controllers.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levelp.controllers.NoteController;

@Component("requestExecutor")
public class RequestExecutor {
    private Gson gson;
    private UserController userController;
    private NoteController noteController;

    @Autowired
    public RequestExecutor(Gson gson, UserController userController, NoteController noteController) {
        this.gson = gson;
        this.userController = userController;
        this.noteController = noteController;
    }

    public ResponseContainer execute(String json, String method, String userId) throws Exception {
        switch (method) {
            case Method.AUTHORIZE:
                RequestContainer<AuthPayload> authRequest =
                        gson.fromJson(json, new TypeToken<RequestContainer<AuthPayload>>() {
                        }.getType());
                return userController.authorize(authRequest.getPayload().getEmail(),
                        authRequest.getPayload().getPwdHash());

            case Method.REGISTRATION:
                RequestContainer<RegisterPayload> regRequest =
                        gson.fromJson(json, new TypeToken<RequestContainer<AuthPayload>>() {
                        }.getType());

                return userController.registration(regRequest.getPayload().getEmail(),
                        regRequest.getPayload().getPwdHash(), regRequest.getPayload().getName());

            case Method.GET_USERS:
                return userController.getUsers();

            case Method.CREATE_NOTE:
                RequestContainer<CreateNotePayload> createNoteRequest =
                        gson.fromJson(json, new TypeToken<RequestContainer<CreateNotePayload>>() {
                        }.getType());
                return noteController.createNote(createNoteRequest.getPayload().getTitle(), createNoteRequest.getPayload().getBody(), userId);

            case Method.EDIT_NOTE:
                RequestContainer<EditNotePayload> editNoteRequest =
                        gson.fromJson(json, new TypeToken<RequestContainer<EditNotePayload>>() {
                        }.getType());
                return noteController.createNote(editNoteRequest.getPayload().getNoteId(),
                        editNoteRequest.getPayload().getTitle(),
                        editNoteRequest.getPayload().getBody());

            case Method.DELETE_NOTE:
                RequestContainer<String> deleteNoteRequest =
                        gson.fromJson(json, new TypeToken<RequestContainer<String>>() {
                        }.getType());
                return noteController.deleteNote(deleteNoteRequest.getPayload());
            case Method.GET_NOTES:
                return noteController.getNotes(userId);
            case Method.ADD_ACCESS_RIGHT:
                throw new Exception("unsupported");
            case Method.REMOVE_ACCESS_RIGHT:
                throw new Exception("unsupported");
        }
        throw new Exception("method " + method + " execution error");
    }

}
