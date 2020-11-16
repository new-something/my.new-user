package com.mynew.auth.user.controller;

import com.mynew.auth.user.service.GithubService;
import com.mynew.auth.user.service.dto.GithubUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class GithubController {

    private final GithubService githubService;

    @GetMapping("/users/github")
    public ResponseEntity<GithubUser> github(
            @RequestParam String code
    ) {
        log.info(code);
        GithubUser githubUser = githubService.getGithubUser(code);
        return ResponseEntity.ok(githubUser);
    }
}

