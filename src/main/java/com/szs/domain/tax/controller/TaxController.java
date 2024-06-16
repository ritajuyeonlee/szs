package com.szs.domain.tax.controller;

import com.szs.domain.tax.service.TaxService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/szs")
public class TaxController {
    private TaxService taxService;

//    @PostMapping
//    @Operation(summary = "스크래핑", description = "스크래핑 API")
//      public ResponseEntity<ScrapResponseDto> scrap(@RequestBody ScrapRequestDto scrapRequestDto) {
//          return ResponseEntity.ok(taxService.scrap(scrapRequestDto));
//      }

}
