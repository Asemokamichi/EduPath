package com.asemokamichi.kz.edupath.controller;

import com.asemokamichi.kz.edupath.exeption.ErrorResponse;
import com.asemokamichi.kz.edupath.exeption.InvalidRequest;
import com.asemokamichi.kz.edupath.exeption.ResourceNotFoundException;
import com.asemokamichi.kz.edupath.exeption.UserAlreadyExists;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExeptionController {

    @ExceptionHandler(InvalidRequest.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequest(InvalidRequest e){
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e){
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExists(UserAlreadyExists e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

}
