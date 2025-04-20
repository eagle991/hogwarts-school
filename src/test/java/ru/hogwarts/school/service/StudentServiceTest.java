package com.example.hogwarts.repository;

import com.example.hogwarts.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    void findByAgeBetween_shouldReturnStudentsInRange() {
        // Given
        Student student1 = new Student(null, "Test1", 15, null);
        Student student2 = new Student(null, "Test2", 20, null);
        studentRepository.save(student1);
        studentRepository.save(student2);

        // When
        var result = studentRepository.findByAgeBetween(15, 20);

        // Then
        assertEquals(2, result.size());
    }
}