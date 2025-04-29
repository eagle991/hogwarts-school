package com.example.hogwarts.controller;

import com.example.hogwarts.TestData;
import com.example.hogwarts.model.Faculty;
import com.example.hogwarts.model.Student;
import com.example.hogwarts.service.FacultyService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FacultyControllerRestTemplateTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private FacultyService facultyService;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/faculty";
    }

    @Test
    void findFacultiesByNameOrColorIgnoreCase_shouldReturnFaculties() {
        Mockito.when(facultyService.findFacultiesByNameOrColorIgnoreCase("griff"))
                .thenReturn(List.of(TestData.GRYFFINDOR));

        ResponseEntity<Faculty[]> response = restTemplate.getForEntity(
                getBaseUrl() + "/filter?search=griff",
                Faculty[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().length);
        assertEquals("Gryffindor", response.getBody()[0].getName());
    }

    @Test
    void getStudentsByFacultyId_shouldReturnStudents() {
        Mockito.when(facultyService.getStudentsByFacultyId(1L))
                .thenReturn(TestData.GRYFFINDOR_STUDENTS);

        ResponseEntity<Student[]> response = restTemplate.getForEntity(
                getBaseUrl() + "/1/students",
                Student[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().length);
        assertEquals("Harry Potter", response.getBody()[0].getName());
    }
}