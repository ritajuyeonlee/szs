package com.szs.domain.member.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

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
