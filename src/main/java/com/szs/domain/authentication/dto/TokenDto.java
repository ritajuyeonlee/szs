package com.szs.domain.authentication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenDto {

    @JsonProperty
     String accessToken;

    public TokenDto(String accessToken){
        this.accessToken = accessToken;
    }

}
