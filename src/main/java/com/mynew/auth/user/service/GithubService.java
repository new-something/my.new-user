package com.mynew.auth.user.service;

import com.mynew.auth.user.service.dto.github.GithubAccessToken;
import com.mynew.auth.user.service.dto.github.GithubUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Log4j2
@Service
@RequiredArgsConstructor
public class GithubService {
    //    로그인 url
    //    https://github.com/login/oauth/authorize?scope=read:user&client_id=2a433252e03305352ce2

    //    access token url
    //    https://github.com/login/oauth/access_token

    //    특정 user 정보 얻는 url
    //    Authorization: token OAUTH-TOKEN    GET https://api.github.com/user

    private final WebClient webClient;

    public GithubUser getGithubUser(final String code) {
        String auth = "token " + accessToken(code);
        GithubUser user = webClient.get()
                .uri("https://api.github.com/user")
                .header(HttpHeaders.AUTHORIZATION, auth)
                .retrieve()
                .bodyToMono(GithubUser.class)
                .flux()
                .toStream()
                .findFirst()
                .orElse(GithubUser.NONE);

        log.info(user);
        return user;
    }

    private String accessToken(final String code) {
        return webClient.post()
                .uri("https://github.com/login/oauth/access_token")
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .body(
                        BodyInserters.fromFormData("client_id", "2a433252e03305352ce2")
                                .with("client_secret", "2155664999d02d15f1146140914fa08747ca3a31")
                                .with("code", code)
                ).retrieve()
                .onStatus(httpStatus -> httpStatus.is4xxClientError() || httpStatus.is5xxServerError(),
                        clientResponse -> clientResponse.bodyToMono(String.class)
                                .map(RuntimeException::new)
                ).bodyToMono(GithubAccessToken.class)
                .flux()
                .toStream()
                .findFirst()
                .orElse(GithubAccessToken.NONE).getAccessToken();
    }
}
