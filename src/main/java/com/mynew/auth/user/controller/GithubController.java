package com.mynew.auth.user.controller;

import com.mynew.auth.user.controller.dto.LoginCompleteResponse;
import com.mynew.auth.user.service.GithubService;
import com.mynew.auth.user.service.dto.github.GithubAccessToken;
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

    @GetMapping("/github/access-token")
    public ResponseEntity<GithubAccessToken> githubAccessToken(
            @RequestParam String code
    )
    {
        log.info(code);
        return ResponseEntity.ok(githubService.accessToken(code));
    }

    @GetMapping("/github/login/complete")
    public ResponseEntity<LoginCompleteResponse> githubLoginComplete(
            @RequestParam String accessToken
    ){
        String jwt = githubService.jwt(accessToken);
        return ResponseEntity.ok(LoginCompleteResponse.ok(jwt));
    }
}

