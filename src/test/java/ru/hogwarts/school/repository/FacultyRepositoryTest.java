package ru.hogwarts.school.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
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
    @Sql(scripts = {"/schema.sql", "/data.sql"})
    void findByNameIgnoreCase_shouldFindFacultyCaseInsensitive() {
        Optional<Faculty> found = facultyRepository.findByNameIgnoreCase("gryffindor");
        assertThat(found).isPresent();
        assertThat(found.get().getColor()).isEqualTo("Red");
    }

    @Test
    @Sql(scripts = {"/schema.sql", "/data.sql"})
    void findByColor_shouldFindByColor() {
        List<Faculty> redFaculties = facultyRepository.findByColor("Red");
        assertThat(redFaculties)
                .hasSize(1)
                .extracting(Faculty::getName)
                .containsExactly("Gryffindor");
    }

    @Test
    @Sql(scripts = {"/schema.sql", "/data.sql"})
    void findByNameOrColor_shouldFindByNameOrColor() {
        List<Faculty> result = facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(
                "gryffindor", "blue");
        assertThat(result)
                .hasSize(2)
                .extracting(Faculty::getName)
                .containsExactlyInAnyOrder("Gryffindor", "Ravenclaw");
    }
}