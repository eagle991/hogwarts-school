package com.example.hogwarts.controller;

import com.example.hogwarts.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void getNamesStartingWithA_shouldReturnFilteredAndSortedNames() throws Exception {
        when(studentService.getNamesStartingWithA()).thenReturn(List.of("ALICE", "ANDREW"));

        mockMvc.perform(get("/student/names-starting-with-a"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0]").value("ALICE"));
    }

    @Test
    void getAverageAge_shouldReturnAverageAge() throws Exception {
        when(studentService.getAverageAge()).thenReturn(20.5);

        mockMvc.perform(get("/student/average-age"))
                .andExpect(status().isOk())
                .andExpect(content().string("20.5"));
    }
}