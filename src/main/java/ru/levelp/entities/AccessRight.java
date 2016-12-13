package ru.levelp.entities;

import com.google.gson.annotations.Expose;

public class AccessRight {
    @Expose
    private int mode;
    @Expose
    private String userId;

    public AccessRight() {
    }

    public int getMode() {
        return mode;
    }

    public String getUserId() {
        return userId;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
