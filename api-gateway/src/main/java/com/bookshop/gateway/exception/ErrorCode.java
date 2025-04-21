package com.bookshop.gateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public enum ErrorCode {
    INVALID_CREATE_USER(1001, "error when create user", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1999, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    PROFILE_NOT_FOUND(1003, "Profile not found", HttpStatus.BAD_REQUEST),
    ;


    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatusCode getStatusCode() {
        return statusCode;
    }
}
