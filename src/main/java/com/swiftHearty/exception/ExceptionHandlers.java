package com.swiftHearty.exception;

import com.swiftHearty.dto.response.CreateNewUserResponse;
import com.swiftHearty.dto.response.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(UserAlreadyExistException.class)
    public ExceptionResponse handleUserAlreadyExistException(UserAlreadyExistException e) {
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(e.getMessage());
        response.setSuccess(Boolean.FALSE);
        return response;
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ExceptionResponse handleResourcesNotFoundException(ResourceNotFoundException e) {
    ExceptionResponse response = new ExceptionResponse();
        response.setMessage(e.getMessage());
        response.setSuccess(Boolean.FALSE);
        return response;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ExceptionResponse handleIllegalArgumentException(IllegalArgumentException e) {
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(e.getMessage());
        response.setSuccess(Boolean.FALSE);
        return response;
    }
}
