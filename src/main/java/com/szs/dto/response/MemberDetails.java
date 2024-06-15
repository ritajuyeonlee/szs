package com.szs.dto.response;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class MemberDetails extends User {

    private final String userId;

    private MemberDetails(
            String userId,
            String password,
            List<SimpleGrantedAuthority> authorities
    ) {
        super(userId, password, authorities);
        this.userId = userId;
    }

    public MemberDetails(
            String userId,
            String password
    ) {
        this(
                userId,
                password,
                List.of(new SimpleGrantedAuthority("ROLE_"))
        );
    }


    public String getUserId() {
        return userId;
    }
}
