package com.example.hogwarts.repository;

import com.example.hogwarts.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void findByAgeBetween_shouldReturnFilteredStudents() {
        Student student1 = new Student(null, "Harry", 17, null);
        Student student2 = new Student(null, "Ron", 16, null);
        studentRepository.save(student1);
        studentRepository.save(student2);

        List<Student> result = studentRepository.findByAgeBetween(15, 20);

        assertThat(result).hasSize(2);
        assertThat(result).extracting(Student::getName).containsExactlyInAnyOrder("Harry", "Ron");
    }
}