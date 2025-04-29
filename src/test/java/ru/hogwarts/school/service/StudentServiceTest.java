package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository repository;

    @InjectMocks
    private StudentService service;

    @Test
    void createStudent_shouldSaveToRepository() {
        Student student = new Student("Hermione", 18);
        when(repository.save(any())).thenReturn(student);

        Student result = service.createStudent(student);
        verify(repository).save(student);
        assertThat(result.getName()).isEqualTo("Hermione");
    }
}