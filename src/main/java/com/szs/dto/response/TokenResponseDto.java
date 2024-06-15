package com.szs.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenResponseDto {

    @JsonProperty
     String accessToken;

    public TokenResponseDto(String accessToken){
        this.accessToken = accessToken;
    }


}
