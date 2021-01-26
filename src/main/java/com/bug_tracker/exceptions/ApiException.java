package com.bug_tracker.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiException {
    private final String message;
    private final HttpStatus httpStatus;
    private String advice = "";

    public ApiException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public ApiException(String message, HttpStatus httpStatus, String advice) {
        this(message, httpStatus);
        this.advice = advice;
    }
}
