package com.example.controller;

import com.example.exception.MyApplicationException;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Created by pkpk1234 on 2017/5/10.
 */
@RestController
public class RestControllerWithExceptionHandler {

    private final MediaType vndErrorMediaType = MediaType
            .parseMediaType("application/vnd.error");

    @ExceptionHandler(value = MyApplicationException.class)
    public ResponseEntity<VndErrors> exceptionHandler(MyApplicationException exception) {
        String errorMsg = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(this.vndErrorMediaType);
        return new ResponseEntity<>(new VndErrors("exception in RestControllerWithExceptionHandler", errorMsg), httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/trigger-exception")
    public ResponseEntity<?> triggerExcetpion() throws MyApplicationException {
        throw new MyApplicationException("System error");
    }
}
