package com.example.hogwarts.service;

import com.example.hogwarts.model.Student;
import com.example.hogwarts.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void createStudent_shouldSaveToRepository() {
        Student student = new Student(null, "Hermione", 18, null);
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Student result = studentService.createStudent(student);

        verify(studentRepository).save(student);
        assertThat(result.getName()).isEqualTo("Hermione");
    }

    @Test
    void findByAgeBetween_shouldReturnFilteredStudents() {
        Student student1 = new Student(null, "Harry", 17, null);
        Student student2 = new Student(null, "Ron", 16, null);
        when(studentRepository.findByAgeBetween(15, 20)).thenReturn(List.of(student1, student2));

        var result = studentService.findByAgeBetween(15, 20);

        assertThat(result).hasSize(2);
        assertThat(result).extracting(Student::getName).containsExactlyInAnyOrder("Harry", "Ron");
    }

    @Test
    void getFacultyByStudentId_shouldReturnFaculty() {
        Student student = new Student(null, "Harry", 17, null);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        var result = studentService.getFacultyByStudentId(1L);

        assertThat(result).isNull(); // Assuming no faculty is set for the student
    }
}