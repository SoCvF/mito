package com.mitocode.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalErrorHandler {
    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerModelNotFoundException(ModelNotFoundException exc, WebRequest req){
        ErrorResponse res = new ErrorResponse(LocalDateTime.now(), exc.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }

}



