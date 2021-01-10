package com.mynew.auth.global.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AwsHealthController {

    @GetMapping("/")
    public ResponseEntity<String> ok(){
        return ResponseEntity.ok("aws health check in my new user api service");
    }
}
