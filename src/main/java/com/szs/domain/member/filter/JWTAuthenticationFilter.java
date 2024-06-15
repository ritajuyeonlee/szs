package com.szs.domain.member.filter;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.szs.config.TokenConfig;
import com.szs.domain.member.dto.MemberDetails;
import com.szs.domain.member.dto.request.LogInRequestDto;
import com.szs.domain.member.dto.response.TokenResponseDto;
import com.szs.domain.member.service.SecurityResponseService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;
import java.util.Date;


public class JWTAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;

    public JWTAuthenticationFilter(
            AuthenticationManager authenticationManager, ObjectMapper objectMapper) {
        super("/szs/login");
        this.objectMapper = objectMapper;
        setAuthenticationManager(authenticationManager);
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException {

        LogInRequestDto logInRequestDto = objectMapper.readValue(request.getInputStream(), LogInRequestDto.class);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                logInRequestDto.getUserId(), logInRequestDto.getPassword());
        return authenticationManager.authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        MemberDetails memberDetails = (MemberDetails) authResult.getPrincipal();

        String jwtToken = JWT.create()
                .withSubject(memberDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + TokenConfig.accessTokenExpiredIn))
                .withClaim("userId", memberDetails.getUserId())
                .sign(Algorithm.HMAC512(TokenConfig.secret));

        response.addHeader("Authorization", "Bearer " + jwtToken);

        TokenResponseDto token = new TokenResponseDto(jwtToken);
        SecurityResponseService.printResponse(token, response);

    }

}

