package com.szs.domain.scrap.dto;

import com.szs.domain.member.dto.response.UpdateTaxRequestDto;

import java.math.BigDecimal;

public record ScrapResponseDto(BigDecimal totalIncome, BigDecimal deduction, BigDecimal taxCredit) {

    public UpdateTaxRequestDto toUpdateTaxRequestDto() {
        return new UpdateTaxRequestDto(totalIncome, deduction, taxCredit);
    }
}
