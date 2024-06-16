package com.szs.domain.member.service;

import com.szs.domain.member.Member;
import com.szs.domain.member.MemberRepository;
import com.szs.domain.member.dto.request.SignUpRequestDto;
import com.szs.exception.MemberNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class MemberService {

    private static final Logger log = LoggerFactory.getLogger(MemberService.class);
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public String signUp(SignUpRequestDto signUpRequestDto) {
        Member member = memberRepository.save(signUpRequestDto.toEntity().withEncodePassword(passwordEncoder));
        return member.getUserId();
    }


    public BigDecimal getRefund() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findById(authentication.getName()).orElseThrow(MemberNotExistException::new);
        return member.getRefund();
    }
}
