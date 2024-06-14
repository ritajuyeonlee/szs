package com.szs.unit.controller;


import com.szs.controller.SzsController;
import com.szs.dto.SignUpDto;
import com.szs.service.SzsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


@AutoConfigureMockMvc
@WebMvcTest(SzsController.class)
public class SzsControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    SzsService szsService;


    @Test
    @DisplayName("회원가입 API")
    void signUpTest() {
        String userId = "id";
        String password = "password";
        String name = "동탁";
        String regNo = "921108-1582816";

        SignUpDto signUpDto = new SignUpDto(userId, password, name, regNo);

        Mockito.when(szsService.signUp(signUpDto)).thenReturn(userId);
    }
}
