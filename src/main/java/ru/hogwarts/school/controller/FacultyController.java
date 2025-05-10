package com.example.hogwarts.controller;

import com.example.hogwarts.model.Faculty;
import com.example.hogwarts.model.Student;
import com.example.hogwarts.service.FacultyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/filter")
    public List<Faculty> findFacultiesByNameOrColorIgnoreCase(
            @RequestParam String search) {
        return facultyService.findFacultiesByNameOrColorIgnoreCase(search);
    }

    @GetMapping("/{id}/students")
    public List<Student> getStudentsByFacultyId(@PathVariable long id) {
        return facultyService.getStudentsByFacultyId(id);
    }
}
