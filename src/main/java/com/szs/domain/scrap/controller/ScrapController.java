package com.szs.domain.scrap.controller;

import com.szs.domain.scrap.dto.ScrapResponseDto;
import com.szs.domain.scrap.service.ScrapService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/szs/scrap")
public class ScrapController {

    private final ScrapService scrapService;

    public ScrapController(ScrapService scrapService) {
        this.scrapService = scrapService;
    }

    @PostMapping
    @Operation(summary = "스크래핑", description = "스크래핑 API")
    @ResponseStatus(HttpStatus.CREATED)
    public ScrapResponseDto scrap() throws IOException {
        return scrapService.scrap();
    }

}
