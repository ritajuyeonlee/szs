package com.szs.domain.member.dto.response;

import java.math.BigDecimal;

public record UpdateTaxRequestDto(BigDecimal taxBase, BigDecimal taxCredit) {
}
