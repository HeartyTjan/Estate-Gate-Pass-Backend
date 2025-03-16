package com.swiftHearty.exception;

import com.swiftHearty.dto.response.CreateNewUserResponse;
import com.swiftHearty.dto.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String > handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}
