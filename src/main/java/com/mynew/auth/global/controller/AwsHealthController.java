package com.mynew.auth.global.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AwsHealthController {

    @GetMapping("/")
    public ResponseEntity<String> ok(){
        return ResponseEntity.ok("aws health check in my new user api service");
    }

    @GetMapping("/aws/{word}")
    public ResponseEntity<String> getTest(@PathVariable String word){
        return ResponseEntity.ok(String.format("Hello %s", word));
    }

    @PostMapping("/aws/{word}")
    public ResponseEntity<String> postTest(@PathVariable String word) {
        return ResponseEntity.ok(String.format("Hello %s", word));
    }

    @DeleteMapping("/aws/{word}")
    public ResponseEntity<String> deleteTest(@PathVariable String word) {
        return ResponseEntity.ok(String.format("Hello %s", word));
    }

    @PutMapping("/aws/{word}")
    public ResponseEntity<String> putTest(@PathVariable String word) {
        return ResponseEntity.ok(String.format("Hello %s", word));
    }

    @PatchMapping("/aws/{word}")
    public ResponseEntity<String> patchTest(@PathVariable String word) {
        return ResponseEntity.ok(String.format("Hello %s", word));
    }

}
