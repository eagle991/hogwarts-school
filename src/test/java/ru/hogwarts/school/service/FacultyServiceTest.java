package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collections;
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

    private final Faculty faculty = new Faculty(1L, "Gryffindor", "Red");

    @Test
    void createFaculty_shouldSaveAndReturnFaculty() {
        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        Faculty result = facultyService.createFaculty(new Faculty());

        assertThat(result.getName()).isEqualTo("Gryffindor");
        verify(facultyRepository, times(1)).save(any(Faculty.class));
    }

    @Test
    void findFaculty_shouldReturnFacultyWhenExists() {
        when(facultyRepository.findById(1L)).thenReturn(Optional.of(faculty));

        Optional<Faculty> result = facultyService.findFaculty(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getColor()).isEqualTo("Red");
    }

    @Test
    void findByColor_shouldReturnFacultiesList() {
        when(facultyRepository.findByColor("Red")).thenReturn(Collections.singletonList(faculty));

        List<Faculty> result = facultyService.findByColor("Red");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Gryffindor");
    }

    @Test
    void deleteFaculty_shouldCallRepositoryDelete() {
        doNothing().when(facultyRepository).deleteById(1L);

        facultyService.deleteFaculty(1L);

        verify(facultyRepository, times(1)).deleteById(1L);
    }
}