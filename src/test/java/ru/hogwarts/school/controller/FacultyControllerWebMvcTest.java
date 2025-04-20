package com.example.hogwarts.controller;

import com.example.hogwarts.TestData;
import com.example.hogwarts.model.Faculty;
import com.example.hogwarts.model.Student;
import com.example.hogwarts.service.FacultyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FacultyController.class)
class FacultyControllerWebMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyService facultyService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void findFacultiesByNameOrColorIgnoreCase_shouldReturnMatchingFaculties() throws Exception {
        when(facultyService.findFacultiesByNameOrColorIgnoreCase(anyString()))
                .thenReturn(List.of(TestData.GRYFFINDOR));

        mockMvc.perform(MockMvcRequestBuilders.get("/faculty/filter")
                        .param("search", "gryff"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Gryffindor"));
    }

    @Test
    void getStudentsByFacultyId_shouldReturnStudents() throws Exception {
        when(facultyService.getStudentsByFacultyId(1L))
                .thenReturn(TestData.GRYFFINDOR_STUDENTS);

        mockMvc.perform(MockMvcRequestBuilders.get("/faculty/1/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Harry Potter"));
    }

    @Test
    void getStudentsByFacultyId_shouldReturnNotFound() throws Exception {
        when(facultyService.getStudentsByFacultyId(999L))
                .thenThrow(new NotFoundException("Faculty not found"));

        mockMvc.perform(MockMvcRequestBuilders.get("/faculty/999/students"))
                .andExpect(status().isNotFound());
    }
}