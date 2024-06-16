package com.szs.domain.authentication.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SecurityResponseService {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void printResponse(Object responseDto, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        objectMapper.writeValue(response.getWriter(), responseDto);
    }
}