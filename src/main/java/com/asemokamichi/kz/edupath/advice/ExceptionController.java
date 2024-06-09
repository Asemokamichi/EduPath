package com.asemokamichi.kz.edupath.advice;

import com.asemokamichi.kz.edupath.exceptions.ErrorResponse;
import com.asemokamichi.kz.edupath.exceptions.InvalidRequest;
import com.asemokamichi.kz.edupath.exceptions.ResourceNotFoundException;
import com.asemokamichi.kz.edupath.exceptions.UserAlreadyExists;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionController {

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
