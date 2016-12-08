package ru.levelp.entities;

public class AccessRight {
    private int mode;
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
