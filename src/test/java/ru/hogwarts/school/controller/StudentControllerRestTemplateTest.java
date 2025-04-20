package com.example.hogwarts.controller;

import com.example.hogwarts.TestData;
import com.example.hogwarts.model.Faculty;
import com.example.hogwarts.model.Student;
import com.example.hogwarts.service.StudentService;
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
class StudentControllerRestTemplateTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private StudentService studentService;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/student";
    }

    @Test
    void findByAgeBetween_shouldReturnStudents() {
        Mockito.when(studentService.findByAgeBetween(15, 20))
                .thenReturn(List.of(TestData.HARRY, TestData.HERMIONE));

        ResponseEntity<Student[]> response = restTemplate.getForEntity(
                getBaseUrl() + "/filter-by-age?min=15&max=20",
                Student[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().length);
    }

    @Test
    void getFacultyByStudentId_shouldReturnFaculty() {
        Mockito.when(studentService.getFacultyByStudentId(1L))
                .thenReturn(TestData.GRYFFINDOR);

        ResponseEntity<Faculty> response = restTemplate.getForEntity(
                getBaseUrl() + "/1/faculty",
                Faculty.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Gryffindor", response.getBody().getName());
    }
}