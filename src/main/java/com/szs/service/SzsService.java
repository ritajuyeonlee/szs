package com.szs.service;

import com.szs.domain.Member;
import com.szs.domain.MemberRepository;
import com.szs.dto.SignUpDto;
import org.springframework.stereotype.Service;

@Service
public class SzsService {

    private static MemberRepository memberRepository;

    public String signUp(SignUpDto signUpDto) {
        Member member = memberRepository.save(signUpDto.toEntity());
        return member.getUserId();
    }
}
