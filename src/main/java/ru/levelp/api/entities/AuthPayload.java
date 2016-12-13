package ru.levelp.api.entities;

import com.google.gson.annotations.Expose;

public class AuthPayload {
    @Expose
    private String email;
    @Expose
    private String pwdHash;

    public String getEmail() {
        return email;
    }

    public String getPwdHash() {
        return pwdHash;
    }
}
