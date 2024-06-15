package com.szs.service;

import com.szs.domain.Member;
import com.szs.domain.MemberRepository;
import com.szs.dto.request.SignUpRequestDto;
import org.springframework.stereotype.Service;

@Service
public class SzsService {

    private final MemberRepository memberRepository;

    public SzsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public String signUp(SignUpRequestDto signUpRequestDto) {
        Member member = memberRepository.save(signUpRequestDto.toEntity());
        return member.getUserId();
    }
}
