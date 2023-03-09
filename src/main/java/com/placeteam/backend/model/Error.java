package com.placeteam.backend.model;

import com.fasterxml.jackson.annotation.JsonGetter;

public class Error {

    private final String message;
    private final int code;

    public Error(String message, int code) {
        this.message = message;
        this.code = code;
    }

    @JsonGetter("error")
    public String getMessage() {
        return message;
    }

    @JsonGetter("code")
    public int getCode() {
        return code;
    }
}
