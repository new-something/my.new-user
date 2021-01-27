package com.mynew.auth.global;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtResolver {
    private static final String KEY = "my-new-secret-key";
    private static final long EXPIRATION_MINUTES = 259200; // 180일
    private static final Map<String, Object> HEADERS;

    static {
        HEADERS = new HashMap<>();
        HEADERS.put("typ", "JWT");
        HEADERS.put("alg", SignatureAlgorithm.HS256.name());
    }

    public static String createJwt(
            final String id,
            final String userName,
            final String email
    ){
        Map<String, Object> claims = new HashMap<>();

        claims.put("id", id);
        claims.put("userName", userName);
        claims.put("email", email);
        claims.put("roles", new String[]{"USER"});

        return Jwts.builder()
                .setHeader(HEADERS)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(EXPIRATION_MINUTES).toInstant()))
                .signWith(SignatureAlgorithm.HS256, KEY.getBytes())
                .compact();
    }
}
