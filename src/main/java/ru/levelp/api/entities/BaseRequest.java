package ru.levelp.api.entities;

import com.google.gson.annotations.Expose;

public class BaseRequest {
    @Expose
    protected String requestId;
    @Expose
    protected String method;
    @Expose
    protected String token;

    public String getRequestId() {
        return requestId;
    }

    public String getMethod() {
        return method;
    }

    public String getToken() {
        return token;
    }
}
