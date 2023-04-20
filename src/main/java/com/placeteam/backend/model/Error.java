package com.placeteam.backend.model;

import com.fasterxml.jackson.annotation.JsonGetter;

public class Error {

    private final int code;
    private final String message;

    public Error(String message, int code) {
        this.message = message;
        this.code = code;
    }

    @JsonGetter("code")
    public int getCode() {
        return code;
    }

    @JsonGetter("error")
    public String getMessage() {
        return message;
    }
}
