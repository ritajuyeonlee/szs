package com.szs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenConfig {

    public static String secret;
    public static Long accessTokenExpiredIn;

    public TokenConfig(
            @Value("${token.secret}") String secret,
            @Value("${token.accessTokenExpiredIn}") Long accessTokenExpiredIn
    ) {
        TokenConfig.secret = secret;
        TokenConfig.accessTokenExpiredIn = accessTokenExpiredIn;
    }


}
