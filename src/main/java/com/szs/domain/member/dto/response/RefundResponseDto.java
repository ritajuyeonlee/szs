package com.szs.domain.member.dto.response;

public class RefundResponseDto {
   private String 결정세액;

    public RefundResponseDto(String refund) {
        this.결정세액 = refund;
    }

    public String get결정새액() {
        return 결정세액;
    }
}
