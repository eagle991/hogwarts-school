package com.example.hogwarts.controller;

import com.example.hogwarts.service.GeneralService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GeneralController.class)
class GeneralControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GeneralService generalService;

    @Test
    void calculateSum_shouldReturnCorrectValue() throws Exception {
        when(generalService.calculateSum()).thenReturn(500000500000L);

        mockMvc.perform(get("/general/sum"))
                .andExpect(status().isOk())
                .andExpect(content().string("500000500000"));
    }
}