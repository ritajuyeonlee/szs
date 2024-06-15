package com.szs.dto.response;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class MemberDetails extends User {

    private final String userId;

    private MemberDetails(
            String userId,
            String email,
            String password,
            boolean enabled,
            boolean accountNonExpired,
            boolean credentialsNonExpired,
            boolean accountNonLocked,
            List<SimpleGrantedAuthority> authorities
    ) {
        super(email, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
    }

    public MemberDetails(
            String userId,
            String password,
            String role,
            String email,
            boolean enabled,
            boolean accountNonExpired,
            boolean credentialsNonExpired,
            boolean accountNonLocked
    ) {
        this(
                userId,
                password,
                email,
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                List.of(new SimpleGrantedAuthority("ROLE_" + role))
        );
    }


    public String getUserId() {
        return userId;
    }
}
