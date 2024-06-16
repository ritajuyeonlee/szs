package com.szs.domain.scrap.service;

import com.szs.domain.member.service.MemberService;
import com.szs.domain.scrap.dto.ScrapResponseDto;
import com.szs.utils.ScrapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;


@Service
public class ScrapService {

    private final MemberService memberService;
    private final ScrapUtils scrapUtils;

    public ScrapService(
            MemberService memberService,
            ScrapUtils scrapUtils
    ) {
        this.memberService = memberService;
        this.scrapUtils = scrapUtils;
    }


    @Transactional
    public ScrapResponseDto scrap() throws IOException {
        ScrapResponseDto scrapResponseDto = scrapUtils.szsScrap();
        memberService.updateTax(scrapResponseDto.toUpdateTaxRequestDto());
        return scrapResponseDto;
    }
}
