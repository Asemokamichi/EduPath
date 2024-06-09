package com.asemokamichi.kz.edupath.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@AllArgsConstructor
@ResponseStatus(HttpStatus.CONFLICT)
public class ErrorResponse {
    private String message;
}
