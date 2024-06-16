package com.szs.domain.member.service;

import com.szs.domain.member.Member;
import com.szs.domain.member.MemberRepository;
import com.szs.domain.member.dto.request.SignUpRequestDto;
import com.szs.domain.member.dto.response.GetRefundResponseDto;
import com.szs.domain.member.dto.response.UpdateTaxRequestDto;
import com.szs.exception.MemberNotExistException;
import com.szs.utils.FormatUtils;
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
    private final FormatUtils formatUtils;

    public MemberService(
            MemberRepository memberRepository,
            PasswordEncoder passwordEncoder,
            FormatUtils formatUtils) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.formatUtils = formatUtils;
    }

    @Transactional
    public String signUp(SignUpRequestDto signUpRequestDto) {
        Member member = memberRepository.save(signUpRequestDto.toEntity().withEncodePassword(passwordEncoder));
        return member.getUserId();
    }


    public GetRefundResponseDto getRefund() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findById(authentication.getName()).orElseThrow(MemberNotExistException::new);
        return new GetRefundResponseDto(formatUtils.bigDecimalFormatting(member.getRefund()));
    }

    @Transactional
    public void updateTax(UpdateTaxRequestDto updateTaxRequestDto) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findById(authentication.getName()).orElseThrow(MemberNotExistException::new);
        member.updateTax(updateTaxRequestDto);
    }
}
