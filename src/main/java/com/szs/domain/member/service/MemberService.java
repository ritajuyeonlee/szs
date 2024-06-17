package com.szs.domain.member.service;

import com.szs.domain.member.dto.request.SignUpRequestDto;
import com.szs.domain.member.dto.response.GetRefundResponseDto;
import com.szs.domain.member.dto.response.UpdateTaxRequestDto;
import com.szs.domain.member.entity.Member;
import com.szs.domain.member.repository.MemberRepository;
import com.szs.exception.MemberNotExistException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;


@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(
            MemberRepository memberRepository,
            PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public String signUp(SignUpRequestDto signUpRequestDto) {
        Member member = memberRepository.save(signUpRequestDto.toEntity().withEncodePassword(passwordEncoder));
        return member.getUserId();
    }


    @Transactional(readOnly = true)
    public GetRefundResponseDto getRefund() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findById(authentication.getName()).orElseThrow(MemberNotExistException::new);
        return new GetRefundResponseDto(member.getRefund());
    }

    @Transactional
    public void updateTax(UpdateTaxRequestDto updateTaxRequestDto) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findById(authentication.getName()).orElseThrow(MemberNotExistException::new);
        member.updateTax(updateTaxRequestDto);
    }
}
