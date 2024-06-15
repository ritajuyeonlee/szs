package com.szs.dto.response;

public class LogInResponseDto {
    private String accessToken;

    public LogInResponseDto(String accessToken){
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
