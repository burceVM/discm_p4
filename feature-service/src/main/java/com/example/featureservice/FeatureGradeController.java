package com.example.featureservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
public class FeatureGradeController {

    private final RestTemplate restTemplate;

    public FeatureGradeController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/feature/grades")
    public List<Map<String, Object>> getGrades() {
        // Call the database service for grades
        String databaseServiceUrl = "http://localhost:8081/grades";
        return restTemplate.getForObject(databaseServiceUrl, List.class);
    }
}
