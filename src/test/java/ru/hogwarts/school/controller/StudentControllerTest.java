package com.example.hogwarts.controller;

import com.example.hogwarts.service.StudentService;
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

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void getStudentCount_shouldReturnCount() throws Exception {
        when(studentService.getStudentCount()).thenReturn(10L);

        mockMvc.perform(get("/student/count"))
                .andExpect(status().isOk())
                .andExpect(content().string("10"));
    }

    @Test
    void getAverageStudentAge_shouldReturnAverageAge() throws Exception {
        when(studentService.getAverageStudentAge()).thenReturn(17.5);

        mockMvc.perform(get("/student/average-age"))
                .andExpect(status().isOk())
                .andExpect(content().string("17.5"));
    }
}