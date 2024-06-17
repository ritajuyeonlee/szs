package com.szs.domain.member.controller;


import com.szs.domain.member.dto.request.SignUpRequestDto;
import com.szs.domain.member.dto.response.GetRefundResponseDto;
import com.szs.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/szs")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "회원가입 API")
    @ResponseStatus(HttpStatus.CREATED)
    public String signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        return memberService.signUp(signUpRequestDto);
    }


    @GetMapping("/refund")
    @Operation(summary = "결정세액조회", description = "결정세액조회 API")
    @ResponseStatus(HttpStatus.OK)
    public GetRefundResponseDto getRefund() {
        return memberService.getRefund();
    }
}
