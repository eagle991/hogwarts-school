package com.example.hogwarts;

import com.example.hogwarts.model.Faculty;
import com.example.hogwarts.model.Student;
import java.util.List;

public class TestData {
    public static final Faculty GRYFFINDOR = new Faculty(1L, "Gryffindor", "red");
    public static final Faculty SLYTHERIN = new Faculty(2L, "Slytherin", "green");

    public static final Student HARRY = new Student(1L, "Harry Potter", 17, GRYFFINDOR);
    public static final Student HERMIONE = new Student(2L, "Hermione Granger", 18, GRYFFINDOR);
    public static final Student DRACO = new Student(3L, "Draco Malfoy", 17, SLYTHERIN);

    public static final List<Student> GRYFFINDOR_STUDENTS = List.of(HARRY, HERMIONE);
    public static final List<Student> ALL_STUDENTS = List.of(HARRY, HERMIONE, DRACO);
}