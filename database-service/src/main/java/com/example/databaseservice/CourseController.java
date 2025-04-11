package com.example.databaseservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @GetMapping
    public List<Map<String, Object>> getCourses() {
        List<Map<String, Object>> courses = new ArrayList<>();
        courses.add(Map.of("name", "Math 101", "units", 3.0f));
        courses.add(Map.of("name", "History 201", "units", 4.0f));
        return courses;
    }
}


