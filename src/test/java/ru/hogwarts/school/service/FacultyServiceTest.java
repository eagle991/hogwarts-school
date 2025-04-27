package com.example.hogwarts.service;

import com.example.hogwarts.model.Faculty;
import com.example.hogwarts.repository.FacultyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class FacultyServiceTest {

    @Mock
    private FacultyRepository facultyRepository;

    @InjectMocks
    private FacultyService facultyService;

    private Faculty gryffindor;
    private Faculty ravenclaw;

    @BeforeEach
    void setUp() {
        gryffindor = new Faculty(1L, "Gryffindor", "Red");
        ravenclaw = new Faculty(2L, "Ravenclaw", "Blue");
    }

    @Test
    void createFaculty_shouldSaveFaculty() {
        when(facultyRepository.save(any(Faculty.class))).thenReturn(gryffindor);

        Faculty created = facultyService.createFaculty(new Faculty());

        assertThat(created.getName()).isEqualTo("Gryffindor");
        verify(facultyRepository).save(any(Faculty.class));
    }

    @Test
    void findFaculty_shouldReturnFacultyWhenExists() {
        when(facultyRepository.findById(1L)).thenReturn(Optional.of(gryffindor));

        Optional<Faculty> found = facultyService.findFaculty(1L);

        assertThat(found).isPresent();
        assertThat(found.get()).isEqualTo(gryffindor);
    }

    @Test
    void findFacultiesByNameOrColorIgnoreCase_shouldReturnMatchingFaculties() {
        when(facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase("gryffindor", "blue"))
                .thenReturn(List.of(gryffindor, ravenclaw));

        List<Faculty> result = facultyService.findFacultiesByNameOrColorIgnoreCase("gryffindor");

        assertThat(result).hasSize(2);
        assertThat(result).extracting(Faculty::getName).containsExactlyInAnyOrder("Gryffindor", "Ravenclaw");
    }
}