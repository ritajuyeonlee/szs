package com.szs.unit.controller;


import com.google.gson.Gson;
import com.szs.domain.member.controller.MemberController;
import com.szs.domain.member.dto.request.SignUpRequestDto;
import com.szs.domain.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@WebMvcTest(MemberController.class)
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MemberService memberService;


    @Test
    @WithMockUser
    @DisplayName("회원가입 API")
    void signUpTest() throws Exception {
        String userId = "id";
        String password = "password";
        String name = "동탁";
        String regNo = "921108-1582816";

        SignUpRequestDto signUpRequestDto = new SignUpRequestDto(userId, password, name, regNo);


        Gson gson = new Gson();
        String content = gson.toJson(signUpRequestDto);

        BDDMockito.given(memberService.signUp(signUpRequestDto)).willReturn(userId);

        mockMvc.perform(post("/szs/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                        .with(csrf())
                ).andExpect(status().isOk());

        verify(memberService).signUp(refEq(signUpRequestDto));
    }
}
