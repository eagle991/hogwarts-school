package ru.hogwarts.school.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.hogwarts.school.model.Faculty;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FacultyRepositoryTest {

    @Autowired
    private FacultyRepository facultyRepository;

    @Test
    void save_shouldPersistFaculty() {
        Faculty faculty = new Faculty();
        faculty.setName("Ravenclaw");
        faculty.setColor("Blue");

        Faculty savedFaculty = facultyRepository.save(faculty);

        assertThat(savedFaculty.getId()).isNotNull();
        assertThat(savedFaculty.getName()).isEqualTo("Ravenclaw");
    }

    @Test
    void findByColor_shouldReturnFaculties() {
        Faculty faculty = new Faculty();
        faculty.setName("Hufflepuff");
        faculty.setColor("Yellow");
        facultyRepository.save(faculty);

        List<Faculty> result = facultyRepository.findByColor("Yellow");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Hufflepuff");
    }

    @Test
    void findByNameIgnoreCase_shouldReturnFaculty() {
        facultyRepository.save(new Faculty(null, "Slytherin", "Green"));

        Optional<Faculty> result = facultyRepository.findByNameIgnoreCase("slytherin");

        assertThat(result).isPresent();
        assertThat(result.get().getColor()).isEqualTo("Green");
    }
}