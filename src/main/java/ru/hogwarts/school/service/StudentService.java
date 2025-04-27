package com.example.hogwarts.service;

import com.example.hogwarts.model.Student;
import com.example.hogwarts.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public long getStudentCount() {
        return studentRepository.countAllStudents();
    }

    public double getAverageStudentAge() {
        return studentRepository.calculateAverageAge();
    }

    public List<Student> getLastFiveStudents() {
        List<Student> students = studentRepository.findLastFiveStudents();
        return students.size() > 5 ? students.subList(0, 5) : students;
    }
}