package com.szs.unit.service;

import com.szs.domain.member.service.MemberService;
import com.szs.domain.scrap.dto.ScrapResponseDto;
import com.szs.domain.scrap.service.ScrapService;
import com.szs.utils.ScrapUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@DisplayName("[단위테스트] ScrapService")
@ExtendWith(MockitoExtension.class)
public class ScrapServiceTest {

    @Mock
    MemberService memberService;

    @Mock
    ScrapUtils scrapUtils;

    @InjectMocks
    private ScrapService scrapService;

    @Test
    @DisplayName("스크래핑")
    void given_Request_when_Scrap_then_ReturnId() throws IOException {
        BigDecimal totalIncome = BigDecimal.TEN;
        BigDecimal deduction = BigDecimal.TEN;
        BigDecimal taxCredit = BigDecimal.TEN;
        ScrapResponseDto szsScrapResponseDto = new ScrapResponseDto(totalIncome, deduction, taxCredit);
        given(scrapUtils.szsScrap()).willReturn(szsScrapResponseDto);

        ScrapResponseDto result = scrapService.scrap();

        assertThat(result).isEqualTo(szsScrapResponseDto);
        // 테스트의 결과 체크가 미흡한거 같아요
    }


}
