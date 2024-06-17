package com.szs.domain.member.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

// DTO에 lombok 도입을 적극적으로 고려해보세요 
public class GetRefundResponseDto {
    @JsonProperty("결정세액")
    private String refund;

    public GetRefundResponseDto(String refund) {
        this.refund = refund;
    }

    public String getRefund() {
        return refund;
    }
}
