package com.szs.service;

import com.szs.domain.Member;
import com.szs.domain.MemberRepository;
import com.szs.dto.request.LogInRequestDto;
import com.szs.dto.request.SignUpRequestDto;
import com.szs.exception.InvalidInformationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SzsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public SzsService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public String signUp(SignUpRequestDto signUpRequestDto) {
        Member member = memberRepository.save(signUpRequestDto.toEntity().withEncodePassword(passwordEncoder));
        return member.getUserId();
    }

    @Transactional
    public String logIn(LogInRequestDto logInRequestDto) {

        Member member = memberRepository.findById(logInRequestDto.getUserId()).orElseThrow(InvalidInformationException::new);
        if (!passwordEncoder.matches(logInRequestDto.getPassword(), member.getPassword())) {
            throw new InvalidInformationException();

            String token = JwtTokenUtil.generateToken(userName, secretKey, expireTimeMs);


        }


    }
