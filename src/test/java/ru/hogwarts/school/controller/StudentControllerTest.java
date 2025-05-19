package com.example.hogwarts.controller;

import com.example.hogwarts.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void printStudentsParallel_shouldReturnOk() throws Exception {
        when(studentService.getAllStudents()).thenReturn(Arrays.asList(
                new Student(1L, "Harry", 17),
                new Student(2L, "Hermione", 18),
                new Student(3L, "Ron", 17),
                new Student(4L, "Draco", 17),
                new Student(5L, "Neville", 17),
                new Student(6L, "Luna", 16)
        ));

        mockMvc.perform(get("/students/print-parallel"))
                .andExpect(status().isOk());
    }

    @Test
    void printStudentsSynchronized_shouldReturnOk() throws Exception {
        when(studentService.getAllStudents()).thenReturn(Arrays.asList(
                new Student(1L, "Harry", 17),
                new Student(2L, "Hermione", 18),
                new Student(3L, "Ron", 17),
                new Student(4L, "Draco", 17),
                new Student(5L, "Neville", 17),
                new Student(6L, "Luna", 16)
        ));

        mockMvc.perform(get("/students/print-synchronized"))
                .andExpect(status().isOk());
    }
}