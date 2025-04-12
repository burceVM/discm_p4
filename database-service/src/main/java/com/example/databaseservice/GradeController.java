package com.example.databaseservice;

import com.example.databaseservice.entity.Grade;
import com.example.databaseservice.repository.GradeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GradeController {

    private final GradeRepository gradeRepository;

    public GradeController(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @GetMapping("/grades")
    public List<Grade> getGrades() {
        return gradeRepository.findAll();
    }

    @GetMapping("/grades/{userId}")
    public List<Grade> getGradesByUserId(@PathVariable Long userId) {
        return gradeRepository.findByUserId(userId);
    }
}