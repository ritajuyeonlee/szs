package com.szs.domain.scrap.dto;

import com.szs.domain.member.dto.response.UpdateTaxRequestDto;

import java.math.BigDecimal;

public record ScrapResponseDto(BigDecimal totalIncome, BigDecimal deduction, BigDecimal taxCredit) {

    public UpdateTaxRequestDto toUpdateTaxRequestDto() {
        // DTO에서 연산을 하는건 좋지 않아 보여요
        return new UpdateTaxRequestDto(totalIncome.subtract(deduction), taxCredit);
    }
}
