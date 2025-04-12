package com.example.featureservice.controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/courses")
public class FeatureCourseController {

    private final RestTemplate restTemplate;

    public FeatureCourseController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public List<Map<String, Object>> getCourses() {
        String databaseServiceUrl = "http://localhost:8081/courses"; // URL of Database-Service
        return restTemplate.exchange(databaseServiceUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Map<String, Object>>>() {}).getBody();
    }
}
