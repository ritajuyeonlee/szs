package com.szs.domain.member.dto.request;

import com.szs.domain.member.Member;

public class SignUpRequestDto {
    private String userId;
    private String password;
    private String name;
    private String regNo;

    public SignUpRequestDto(){}

    public SignUpRequestDto(String userId, String password, String name, String regNo) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.regNo = regNo;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getRegNo() {
        return regNo;
    }

    public String getUserId() {
        return userId;
    }



    public Member toEntity() {
        return Member.of(userId, password, name, regNo);
    }
}

