package com.szs.dto;

import com.szs.domain.Member;

import java.util.Objects;

public class SignUpDto {
    private String userId;
    private String password;
    private String name;
    private String regNo;

    public SignUpDto(String userId, String password, String name, String regNo) {
        this.userId = userId;
        this.name = name;
        this.password=password;
        this.regNo=regNo;
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

