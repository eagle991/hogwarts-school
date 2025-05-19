package com.example.hogwarts.service;

import com.example.hogwarts.model.Student;
import com.example.hogwarts.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }

    public List<Student> findByAgeBetween(int min, int max) {
        logger.info("Was invoked method for finding students by age between {} and {}", min, max);
        return studentRepository.findByAgeBetween(min, max);
    }

    public Student getFacultyByStudentId(long studentId) {
        logger.debug("Trying to find faculty by studentId = {}", studentId);
        return studentRepository.findById(studentId)
                .map(Student::getFaculty)
                .orElseThrow(() -> {
                    logger.error("There is no student with id = {}", studentId);
                    return new RuntimeException("Student not found");
                });
    }
}