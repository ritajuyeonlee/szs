package com.szs.domain.authentication.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.szs.config.TokenConfig;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {


    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String headerString = "Authorization";
        String prefix = "Bearer ";

        String header = request.getHeader(headerString);
        if (header != null && header.startsWith(prefix)) {
            String token = request.getHeader(headerString)
                    .replace(prefix, "");

            String userId = JWT.require(Algorithm.HMAC512(TokenConfig.secret)).build().verify(token)
                    .getClaim("userId").asString();

            if (userId != null) {
                Authentication authentication = new UsernamePasswordAuthenticationToken(userId, null);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        doFilter(request, response, chain);
    }
}
