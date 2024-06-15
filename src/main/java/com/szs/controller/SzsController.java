package com.szs.controller;


import com.szs.dto.request.SignUpRequestDto;
import com.szs.service.SzsService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/szs")
public class SzsController {

    private final SzsService szsService;

    @Autowired
    public SzsController(SzsService szsService) {
        this.szsService = szsService;
    }


    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "회원가입 API")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        return ResponseEntity.ok(szsService.signUp(signUpRequestDto));
    }



/*
    @PostMapping("/scrap")
    @Operation(summary = "스크래핑", description = "스크래핑 API")
    public ResponseEntity<ScrapResponseDto> scrap(@RequestBody SignUpRequestDto signUpRequestDto) {
        return ResponseEntity.ok(szsService.scrap(signUpRequestDto));
    }*/

}
