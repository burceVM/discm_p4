package com.example.viewservice;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/courses")
public class ViewCourseController {

    private final RestTemplate restTemplate;

    public ViewCourseController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String showCourses(Model model) {
        String featureServiceUrl = "http://localhost:8082/api/courses"; // URL of Feature-Service
        List<Map<String, Object>> courses = restTemplate.exchange(featureServiceUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Map<String, Object>>>() {}).getBody();
        model.addAttribute("courses", courses);
        return "courses"; // Thymeleaf template name
    }
}
