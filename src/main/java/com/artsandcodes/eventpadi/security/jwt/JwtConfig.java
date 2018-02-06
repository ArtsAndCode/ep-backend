package com.artsandcodes.eventpadi.security.jwt;

import org.springframework.stereotype.Service;

/**
 * Created by prestige on 11/27/2017.
 */

@Service
public class JwtConfig {

    private final String secret = "otuonye";
    private final long tokenExpireTimeInMilli = 1 * 24 * 60 * 60 * 1000 ; // day * hours * minutes * seconds * milliseconds
    private final long refreshTokenExpireTimeInMilli = 7 * 24 * 60 * 60 * 1000 ; // day * hours * minutes * seconds * milliseconds
    private final String header = "Authorization";
    private final String refreshHeader = "Refresh-Token";

    public String getSecret() {
        return secret;
    }

    public long getTokenExpireTimeInMilli() {
        return tokenExpireTimeInMilli;
    }

    public long getRefreshTokenExpireTimeInMilli() {
        return refreshTokenExpireTimeInMilli;
    }

    public String getHeader() {
        return header;
    }

    public String getRefreshHeader() {
        return refreshHeader;
    }
}
