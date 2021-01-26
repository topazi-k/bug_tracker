package com.bug_tracker.exceptions;

import com.bug_tracker.exceptions.custom.DaoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ApiExceptionsHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleException(NoSuchElementException e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(e.getMessage(), httpStatus);
        return new ResponseEntity(apiException, httpStatus);
    }

    @ExceptionHandler(DaoException.class)
    public ResponseEntity<Object> handleDaoExceptin(DaoException e){
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiException apiException = new ApiException(e.getMessage(), httpStatus);
        return new ResponseEntity(apiException, httpStatus);
    }
}
