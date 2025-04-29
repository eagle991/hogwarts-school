package ru.hogwarts.school.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.hogwarts.school.model.Student;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository repository;

    @Test
    void findByAge_shouldReturnStudents() {
        Student student = new Student("Harry", 17);
        repository.save(student);

        List<Student> result = repository.findByAge(17);
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Harry");
    }
}