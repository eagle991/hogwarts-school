package com.example.hogwarts.repository;

import com.example.hogwarts.model.Faculty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class FacultyRepositoryTest {
    @Autowired
    private FacultyRepository facultyRepository;

    @Test
    void findByNameIgnoreCaseOrColorIgnoreCase_shouldFindByColor() {
        // Given
        Faculty faculty = new Faculty(null, "Test", "blue");
        facultyRepository.save(faculty);

        // When
        var result = facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase("", "BLUE");

        // Then
        assertEquals(1, result.size());
        assertEquals("Test", result.get(0).getName());
    }
}