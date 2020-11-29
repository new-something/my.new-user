package com.mynew.auth.user.controller.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class LoginCompleteResponse {
    private final String jwt;

    public static LoginCompleteResponse ok(String jwt) {
        return new LoginCompleteResponse(jwt);
    }
}
