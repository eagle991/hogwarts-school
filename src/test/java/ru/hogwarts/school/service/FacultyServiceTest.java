package ru.hogwarts.school.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

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
    void editFaculty_shouldUpdateFaculty() {
        Faculty updated = new Faculty(1L, "Gryffindor", "Scarlet");
        when(facultyRepository.save(any(Faculty.class))).thenReturn(updated);

        Faculty result = facultyService.editFaculty(updated);
        assertThat(result.getColor()).isEqualTo("Scarlet");
        verify(facultyRepository).save(updated);
    }

    @Test
    void deleteFaculty_shouldCallDelete() {
        doNothing().when(facultyRepository).deleteById(1L);

        facultyService.deleteFaculty(1L);
        verify(facultyRepository).deleteById(1L);
    }

    @Test
    void findByColor_shouldReturnMatchingFaculties() {
        when(facultyRepository.findByColor("Red")).thenReturn(List.of(gryffindor));

        List<Faculty> result = facultyService.findByColor("Red");
        assertThat(result)
                .hasSize(1)
                .containsExactly(gryffindor);
    }

    @Test
    void findByName_shouldReturnMatchingFaculty() {
        when(facultyRepository.findByNameIgnoreCase("gryffindor")).thenReturn(Optional.of(gryffindor));

        Optional<Faculty> result = facultyService.findByName("gryffindor");
        assertThat(result).contains(gryffindor);
    }

    @Test
    void getAllFaculties_shouldReturnAllFaculties() {
        when(facultyRepository.findAll()).thenReturn(List.of(gryffindor, ravenclaw));

        List<Faculty> allFaculties = facultyService.getAllFaculties();
        assertThat(allFaculties)
                .hasSize(2)
                .containsExactlyInAnyOrder(gryffindor, ravenclaw);
    }

    @Test
    void findByNameOrColor_shouldReturnMatchingFaculties() {
        when(facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase("gryffindor", "blue"))
                .thenReturn(List.of(gryffindor, ravenclaw));

        List<Faculty> result = facultyService.findByNameOrColor("gryffindor", "blue");
        assertThat(result)
                .hasSize(2)
                .containsExactlyInAnyOrder(gryffindor, ravenclaw);
    }
}