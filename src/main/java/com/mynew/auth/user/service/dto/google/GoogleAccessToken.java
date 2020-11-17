package com.mynew.auth.user.service.dto.google;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public final class GoogleAccessToken {
    public static final GoogleAccessToken NONE = new GoogleAccessToken();
    private String accessToken;
    private Integer expiresIn;
    private String scope;
    private String tokenType;
    private String idToken;
}
