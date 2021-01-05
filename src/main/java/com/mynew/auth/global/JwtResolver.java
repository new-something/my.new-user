package com.mynew.auth.global;

import com.mynew.auth.user.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.time.ZonedDateTime;
import java.util.*;

@Log4j2
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtResolver {
    private static final String KEY = "my-new-secret-key";
    private static final long EXPIRATION_MINUTES = 21600; // 15일
    private static final Map<String, Object> HEADERS;

    static {
        HEADERS = new HashMap<>();
        HEADERS.put("typ", "JWT");
        HEADERS.put("alg", SignatureAlgorithm.HS256.name());
    }

    public static String createJwt(
            final long id,
            final String userName,
            final String email
    ){
        Map<String, Object> claims = new HashMap<>();

        claims.put("id", id);
        claims.put("userName", userName);
        claims.put("email", email);

        return Jwts.builder()
                .setHeader(HEADERS)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(EXPIRATION_MINUTES).toInstant()))
                .signWith(SignatureAlgorithm.HS256, KEY.getBytes())
                .compact();
    }

    public static boolean isExpired(final String jwt) {
        Claims claims = parseJwtToClaims(jwt);
        if (Objects.isNull(claims)) {
            return false;
        }

        Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }

    public static Claims parseJwtToClaims(final String jwt ){
        try {

            return Jwts.parser()
                    .setSigningKey(KEY.getBytes())
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (JwtException e) {
            return null;
        }
    }

    public static User parseJwtToUser(final String jwt){
        Claims claims = parseJwtToClaims(jwt);
        if (Objects.isNull(claims)) {
            return User.EMPTY;
        }

        long id = claims.get("id", Long.class);
        String nickName = claims.get("userName", String.class);
        String email = claims.get("email", String.class);

        return User.builder()
                .userId(id)
                .userName(nickName)
                .email(email)
                .build();
    }
}
