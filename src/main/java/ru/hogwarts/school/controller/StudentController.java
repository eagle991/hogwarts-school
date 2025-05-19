package com.example.hogwarts.controller;

import com.example.hogwarts.model.Student;
import com.example.hogwarts.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/print-parallel")
    public void printStudentsParallel() {
        List<Student> students = studentService.getAllStudents();

        // Первые два имени в основном потоке
        System.out.println(students.get(0).getName());
        System.out.println(students.get(1).getName());

        // Третье и четвертое имя в параллельном потоке
        new Thread(() -> {
            System.out.println(students.get(2).getName());
            System.out.println(students.get(3).getName());
        }).start();

        // Пятое и шестое имя в другом параллельном потоке
        new Thread(() -> {
            System.out.println(students.get(4).getName());
            System.out.println(students.get(5).getName());
        }).start();
    }

    @GetMapping("/print-synchronized")
    public void printStudentsSynchronized() {
        List<Student> students = studentService.getAllStudents();

        // Первые два имени в основном потоке
        printSynchronized(students.get(0).getName());
        printSynchronized(students.get(1).getName());

        // Третье и четвертое имя в параллельном потоке
        new Thread(() -> {
            printSynchronized(students.get(2).getName());
            printSynchronized(students.get(3).getName());
        }).start();

        // Пятое и шестое имя в другом параллельном потоке
        new Thread(() -> {
            printSynchronized(students.get(4).getName());
            printSynchronized(students.get(5).getName());
        }).start();
    }

    // Синхронизированный метод для печати имен
    private synchronized void printSynchronized(String name) {
        System.out.println(name);
    }
}