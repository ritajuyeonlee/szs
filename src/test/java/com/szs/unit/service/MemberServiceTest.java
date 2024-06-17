package com.szs.unit.service;

import com.szs.domain.authentication.dto.MemberDetails;
import com.szs.domain.member.dto.request.SignUpRequestDto;
import com.szs.domain.member.dto.response.UpdateTaxRequestDto;
import com.szs.domain.member.entity.Member;
import com.szs.domain.member.repository.MemberRepository;
import com.szs.domain.member.service.MemberService;
import com.szs.exception.MemberNotExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@DisplayName("[단위테스트] MemberService")
@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @BeforeEach
    void setUp() {
        UserDetails user = new MemberDetails("kw68", "123456");

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities()));
    }

    @Mock
    MemberRepository memberRepository;

    @Mock
    PasswordEncoder passwordEncoder;


    @InjectMocks
    private MemberService memberService;

    @Test
    @DisplayName("회원가입")
    void given_Request_when_SignUp_then_ReturnId() {
        String userId = "kw68";
        String password = "123456";
        String name = "관우";
        String regNo = "681108-1582816";
        SignUpRequestDto signUpRequestDto = new SignUpRequestDto(userId, password, name, regNo);
        Member member = Member.of(userId, password, name, regNo);

        given(memberRepository.save(any())).willReturn(member);

        String result = memberService.signUp(signUpRequestDto);

        assertThat(result).isEqualTo(userId);
    }

    @Test
    @DisplayName("결정세액 조회시 회원이 없는경우 Exception")
    void given_InvalidMember_when_GetRefund_then_Exception() {

        given(memberRepository.findById(any())).willThrow(MemberNotExistException.class);

        Assertions.assertThrows(MemberNotExistException.class, () -> {
            memberService.getRefund();
        });
    }

    @Test
    @DisplayName("세금 업데이트시 회원이 없는경우 Exception")
    void given_InvalidMember_when_UpdateTax_then_Exception() {
        UpdateTaxRequestDto updateTaxRequestDto = new UpdateTaxRequestDto(BigDecimal.TEN, BigDecimal.TEN,BigDecimal.TEN);
        given(memberRepository.findById(any())).willThrow(MemberNotExistException.class);

        Assertions.assertThrows(MemberNotExistException.class, () -> {
            memberService.updateTax(updateTaxRequestDto);
        });
    }

}
