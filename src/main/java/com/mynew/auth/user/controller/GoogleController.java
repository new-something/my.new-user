package com.mynew.auth.user.controller;

import com.mynew.auth.user.controller.dto.LoginCompleteResponse;
import com.mynew.auth.user.service.GoogleService;
import com.mynew.auth.user.service.dto.google.GoogleAccessToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class GoogleController {

    private final GoogleService googleService;

    @GetMapping("/google/login/complete")
    public ResponseEntity<LoginCompleteResponse> githubJwt(
            @RequestParam String code
    )
    {
        log.info(code);
        GoogleAccessToken accessToken = googleService.accessToken(code);
        String jwt = googleService.jwt(accessToken);

        log.info("google login complete");
        return ResponseEntity.ok(LoginCompleteResponse.ok(jwt));
    }
}
