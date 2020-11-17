package com.mynew.auth.user.service;

import com.mynew.auth.user.service.dto.google.GoogleAccessToken;
import com.mynew.auth.user.service.dto.google.GoogleUser;
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
public class GoogleService {

//    scope
//    userinfo.email
//    userinfo.profile

    //로그인 url
//    https://accounts.google.com/o/oauth2/v2/auth?
//        client_id=737679013674-q9efp32jd44hu4gmetvavqr64d9rj97h.apps.googleusercontent.com&
//        response_type=code&
//        state=state_parameter_passthrough_value&
//        scope=https%3A//www.googleapis.com/auth/userinfo.profile&
//        redirect_uri=http%3A//localhost:8080/users/google&
//        prompt=consent&
//        include_granted_scopes=true

    private static final String PERSON_FIELDS = "addresses,ageRanges,biographies,birthdays," +
            "calendarUrls,clientData,coverPhotos,emailAddresses,events,,externalIds," +
            "genders,imClients,interests,locales,,locations,memberships,metadata,miscKeywords," +
            "names,nicknames,occupations,organizations,phoneNumbers,photos,relations,sipAddresses," +
            "skills,urls,userDefined";

    private final WebClient webClient;

    public String getGoogleUser(final String code) {
        GoogleAccessToken googleAccessToken = accessToken(code);

        String auth = googleAccessToken.getTokenType() + " " + googleAccessToken.getAccessToken();
        String result = webClient.get()
                .uri("https://people.googleapis.com/v1/people/me?personFields="+PERSON_FIELDS)
                .header(HttpHeaders.AUTHORIZATION, auth)
                .retrieve()
                .onStatus(httpStatus -> httpStatus.is4xxClientError() || httpStatus.is5xxServerError(),
                        clientResponse -> clientResponse.bodyToMono(String.class)
                                .map(RuntimeException::new)
                ).bodyToMono(String.class)
                .flux()
                .toStream()
                .findFirst()
                .orElse("");

        log.info(result);
        return result;
    }

    private GoogleAccessToken accessToken(final String code) {
        GoogleAccessToken googleAccessToken = webClient.post()
                .uri("https://oauth2.googleapis.com/token")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .body(
                        BodyInserters.fromFormData("client_secret", "XBnvqLH9rpK9P2aNkSouUhwY")
                                .with("client_id", "737679013674-q9efp32jd44hu4gmetvavqr64d9rj97h.apps.googleusercontent.com")
                                .with("grant_type", "authorization_code")
                                .with("redirect_uri", "http://localhost:8080/users/google")
                                .with("code", code)
                ).retrieve()
                .onStatus(httpStatus -> httpStatus.is4xxClientError() || httpStatus.is5xxServerError(),
                        clientResponse -> clientResponse.bodyToMono(String.class)
                                .map(RuntimeException::new)
                ).bodyToMono(GoogleAccessToken.class)
                .flux()
                .toStream()
                .findFirst()
                .orElse(GoogleAccessToken.NONE);
        log.info(googleAccessToken);

        return googleAccessToken;
    }

}



