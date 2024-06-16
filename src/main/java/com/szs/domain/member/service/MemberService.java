package com.szs.domain.member.service;

import com.szs.domain.member.Member;
import com.szs.domain.member.MemberRepository;
import com.szs.domain.member.dto.request.SignUpRequestDto;
import com.szs.domain.member.dto.response.RefundResponseDto;
import com.szs.exception.MemberNotExistException;
import com.szs.utils.NumberUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.RoundingMode;
import java.util.HashMap;


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


    public RefundResponseDto getRefund() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findById(authentication.getName()).orElseThrow(MemberNotExistException::new);
        return new RefundResponseDto(
                NumberUtils.bigDecimalFormatting(
                        member.getRefund().setScale(0, RoundingMode.HALF_UP)));
    }

    public void getScrap() throws IOException {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "동탁");
        map.put("regNo", "921108-1582816");

        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("X-API-KEY", "aXC8zK6puHIf9l53L8TiQg==");

        Jsoup.connect("https://codetest-v4.3o3.co.kr/scrap")
                .method(Connection.Method.POST)
                .header("X-API-KEY", "aXC8zK6puHIf9l53L8TiQg==")
                .data(map)
                        .execute();

    }
}
