package com.szs.controller;


import com.szs.dto.SignUpDto;
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
    public ResponseEntity<String> signUp(@RequestBody SignUpDto signUpDto) {
        return ResponseEntity.ok(szsService.signUp(signUpDto));
    }


}
