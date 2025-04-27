package ru.hogwarts.school.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FacultyController.class)
class FacultyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyService facultyService;

    private Faculty gryffindor;
    private Faculty ravenclaw;

    @BeforeEach
    void setUp() {
        gryffindor = new Faculty(1L, "Gryffindor", "Red");
        ravenclaw = new Faculty(2L, "Ravenclaw", "Blue");
    }

    @Test
    void createFaculty_shouldReturnCreatedFaculty() throws Exception {
        when(facultyService.createFaculty(any())).thenReturn(gryffindor);

        mockMvc.perform(post("/faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Gryffindor\",\"color\":\"Red\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Gryffindor"))
                .andExpect(jsonPath("$.color").value("Red"));
    }

    @Test
    void getFaculty_shouldReturnFacultyWhenExists() throws Exception {
        when(facultyService.findFaculty(1L)).thenReturn(Optional.of(gryffindor));

        mockMvc.perform(get("/faculty/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Gryffindor"));
    }

    @Test
    void getFaculty_shouldReturn404WhenNotExists() throws Exception {
        when(facultyService.findFaculty(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/faculty/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateFaculty_shouldReturnUpdatedFaculty() throws Exception {
        Faculty updated = new Faculty(1L, "Gryffindor", "Scarlet");
        when(facultyService.updateFaculty(any())).thenReturn(updated);

        mockMvc.perform(put("/faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"Gryffindor\",\"color\":\"Scarlet\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.color").value("Scarlet"));
    }

    @Test
    void deleteFaculty_shouldReturn200() throws Exception {
        doNothing().when(facultyService).deleteFaculty(1L);

        mockMvc.perform(delete("/faculty/1"))
                .andExpect(status().isOk());
    }

    @Test
    void findByColor_shouldReturnFaculties() throws Exception {
        when(facultyService.getFacultiesByColor("Red")).thenReturn(List.of(gryffindor));

        mockMvc.perform(get("/faculty/color/Red"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Gryffindor"));
    }

    @Test
    void findByName_shouldReturnFaculty() throws Exception {
        when(facultyService.getFacultyByName("Gryffindor")).thenReturn(Optional.of(gryffindor));

        mockMvc.perform(get("/faculty/name/Gryffindor"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.color").value("Red"));
    }

    @Test
    void getAll_shouldReturnAllFaculties() throws Exception {
        when(facultyService.getAllFaculties()).thenReturn(List.of(gryffindor, ravenclaw));

        mockMvc.perform(get("/faculty"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
}