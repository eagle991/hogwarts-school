package com.example.hogwarts.service;

import com.example.hogwarts.model.Faculty;
import com.example.hogwarts.model.Student;
import com.example.hogwarts.repository.FacultyRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public List<Faculty> findFacultiesByNameOrColorIgnoreCase(String search) {
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(search, search);
    }

    public List<Student> getStudentsByFacultyId(long facultyId) {
        return facultyRepository.findById(facultyId)
                .map(Faculty::getStudents)
                .orElseThrow(() -> new NotFoundException("Faculty not found"));
    }
}