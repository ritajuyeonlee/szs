package com.szs.dto;

import com.szs.domain.Member;

public class SignUpDto {
    private String userId;
    private String password;
    private String name;
    private String regNo;

    public Member toEntity() {
        return Member.of(userId, password, name, regNo);
    }
}

