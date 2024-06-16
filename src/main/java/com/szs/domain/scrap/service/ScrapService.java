package com.szs.domain.scrap.service;

import com.szs.domain.member.dto.response.UpdateTaxRequestDto;
import com.szs.domain.member.service.MemberService;
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
    public void scrap() throws IOException {
        UpdateTaxRequestDto updateTaxRequestDto = scrapUtils.szsScrap();
        memberService.updateTax(updateTaxRequestDto);
    }
}
