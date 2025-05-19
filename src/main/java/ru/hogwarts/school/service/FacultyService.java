package com.example.hogwarts.service;

import com.example.hogwarts.model.Faculty;
import com.example.hogwarts.repository.FacultyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {
    private static final Logger logger = LoggerFactory.getLogger(FacultyService.class);
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("Was invoked method for create faculty");
        return facultyRepository.save(faculty);
    }

    public Optional<Faculty> findFaculty(Long id) {
        logger.debug("Trying to find faculty with id = {}", id);
        return facultyRepository.findById(id);
    }

    public Faculty updateFaculty(Faculty faculty) {
        logger.info("Was invoked method for update faculty with id = {}", faculty.getId());
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long id) {
        logger.warn("Deleting faculty with id = {}", id);
        facultyRepository.deleteById(id);
    }

    public List<Faculty> getFacultiesByColor(String color) {
        logger.info("Was invoked method for finding faculties by color = {}", color);
        return facultyRepository.findByColor(color);
    }

    public Optional<Faculty> getFacultyByName(String name) {
        logger.debug("Trying to find faculty by name = {}", name);
        return facultyRepository.findByNameIgnoreCase(name);
    }

    public List<Faculty> getAllFaculties() {
        logger.info("Was invoked method for getting all faculties");
        return facultyRepository.findAll();
    }

    public List<Faculty> findFacultiesByNameOrColorIgnoreCase(String search) {
        logger.info("Was invoked method for finding faculties by name or color = {}", search);
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(search, search);
    }

    public List<Student> getStudentsByFacultyId(Long facultyId) {
        logger.debug("Trying to find students by facultyId = {}", facultyId);
        return facultyRepository.findById(facultyId)
                .map(Faculty::getStudents)
                .orElseThrow(() -> {
                    logger.error("There is no faculty with id = {}", facultyId);
                    return new RuntimeException("Faculty not found");
                });
    }
}