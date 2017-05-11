package com.example.controller;

import com.example.exception.MyApplicationException2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pkpk1234 on 2017/5/10.
 */
@RestController
public class RestControllerWithException {

    @GetMapping("/trigger-exception2")
    public ResponseEntity<?> triggerException2() {
        throw new MyApplicationException2("Internal Server Error");
    }

}
