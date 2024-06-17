package com.szs.domain.member.dto.response;

import com.szs.domain.member.entity.value.Tax;

import java.math.BigDecimal;

public record UpdateTaxRequestDto(BigDecimal totalIncome, BigDecimal deduction, BigDecimal taxCredit) {

    public Tax toValue() {
        return Tax.of(totalIncome, deduction, taxCredit);
    }

}
