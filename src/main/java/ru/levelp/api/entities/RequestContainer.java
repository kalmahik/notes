package ru.levelp.api.entities;

import com.google.gson.annotations.Expose;

public class RequestContainer<T> extends BaseRequest {
    @Expose
    private T payload;

    public T getPayload() {
        return payload;
    }
}
