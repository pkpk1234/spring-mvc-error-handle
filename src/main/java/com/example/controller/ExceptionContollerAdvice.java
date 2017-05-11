package com.example.controller;

import com.example.exception.MyApplicationException2;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

/**
 * Created by pkpk1234 on 2017/5/10.
 */

@ControllerAdvice(assignableTypes=RestControllerWithException.class)
public class ExceptionContollerAdvice {
    private final MediaType vndErrorMediaType = MediaType
            .parseMediaType("application/vnd.error");

    @ExceptionHandler(value = MyApplicationException2.class)
    public ResponseEntity<VndErrors> exceptionHandler(MyApplicationException2 exception) {
        String errorMsg = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(this.vndErrorMediaType);
        return new ResponseEntity<>(new VndErrors("exception in RestControllerWithException", errorMsg), httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
