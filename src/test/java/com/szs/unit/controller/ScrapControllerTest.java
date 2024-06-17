package com.szs.unit.controller;


import com.szs.domain.scrap.controller.ScrapController;
import com.szs.domain.scrap.service.ScrapService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(ScrapController.class)
public class ScrapControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ScrapService scrapService;

    @Test
    @WithMockUser
    @DisplayName("스크래핑 API")
    void scrapTest() throws Exception {

        mockMvc.perform(post("/szs/scrap")
                .contentType(MediaType.APPLICATION_JSON)
                .with(csrf())
        ).andExpect(status().isCreated());

        verify(scrapService).scrap();
    }

}
