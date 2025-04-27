package com.example.hogwarts.repository;

import com.example.hogwarts.model.Faculty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class FacultyRepositoryTest {

    @Autowired
    private FacultyRepository facultyRepository;

    @Test
    void findByNameIgnoreCaseOrColorIgnoreCase_shouldFindMatchingFaculties() {
        Faculty gryffindor = new Faculty(null, "Gryffindor", "Red");
        Faculty ravenclaw = new Faculty(null, "Ravenclaw", "Blue");
        facultyRepository.save(gryffindor);
        facultyRepository.save(ravenclaw);

        List<Faculty> result = facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase("gryffindor", "blue");

        assertThat(result).hasSize(2);
        assertThat(result).extracting(Faculty::getName).containsExactlyInAnyOrder("Gryffindor", "Ravenclaw");
    }
}