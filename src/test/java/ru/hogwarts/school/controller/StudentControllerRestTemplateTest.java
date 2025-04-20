package com.example.hogwarts.controller;

import com.example.hogwarts.TestData;
import com.example.hogwarts.model.Faculty;
import com.example.hogwarts.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerRestTemplateTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/student";
    }

    @Test
    void findByAgeBetween_shouldReturnStudentsInAgeRange() {
        ResponseEntity<Student[]> response = restTemplate.getForEntity(
                getBaseUrl() + "/filter-by-age?min=17&max=18",
                Student[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
    }

    @Test
    void getFacultyByStudentId_shouldReturnFacultyForStudent() {
        ResponseEntity<Faculty> response = restTemplate.getForEntity(
                getBaseUrl() + "/1/faculty",
                Faculty.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Gryffindor", response.getBody().getName());
    }

    @Test
    void getFacultyByStudentId_shouldReturnNotFoundForInvalidId() {
        ResponseEntity<Faculty> response = restTemplate.getForEntity(
                getBaseUrl() + "/999/faculty",
                Faculty.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}