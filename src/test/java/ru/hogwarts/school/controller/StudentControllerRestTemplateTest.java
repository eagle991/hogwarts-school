package com.example.hogwarts.controller;

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
    void findByAgeBetween_shouldReturnFilteredStudents() {
        Student student1 = new Student(null, "Harry", 17, null);
        Student student2 = new Student(null, "Ron", 16, null);
        Mockito.when(studentService.findByAgeBetween(15, 20)).thenReturn(List.of(student1, student2));

        ResponseEntity<Student[]> response = restTemplate.getForEntity(
                getBaseUrl() + "/filter-by-age?min=15&max=20",
                Student[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(2);
        assertThat(response.getBody()[0].getName()).isEqualTo("Harry");
    }
}