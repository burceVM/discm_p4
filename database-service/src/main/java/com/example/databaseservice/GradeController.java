package com.example.databaseservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
public class GradeController {
    @GetMapping("/grades")
    public List<Map<String, Object>> getGrades() {
        // Hardcoding grades response for simplicity
        return List.of(
                Map.of(
                        "courseName", "Mathematics",
                        "courseCode", "MATH101",
                        "courseGrade", 85.5,
                        "term", 1,
                        "year", 2023
                ),
                Map.of(
                        "courseName", "History",
                        "courseCode", "HIST202",
                        "courseGrade", 78.0,
                        "term", 2,
                        "year", 2023
                )
        );
    }
}

