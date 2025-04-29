package com.example.hogwarts.repository;

import com.example.hogwarts.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findByColor(String color);
    List<Faculty> findByNameIgnoreCase(String name);
    List<Faculty> findByNameIgnoreCaseOrColorIgnoreCase(String name, String color);
}