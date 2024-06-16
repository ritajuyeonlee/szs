package com.szs.domain.scrap.controller;

import com.szs.domain.scrap.service.ScrapService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public void scrap() throws IOException {
        scrapService.scrap();
    }

}
