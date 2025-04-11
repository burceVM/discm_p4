package com.example.viewservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;

@Controller
public class ViewGradeController {

    private final RestTemplate restTemplate;

    public ViewGradeController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/grades")
    public String showGrades(Model model) {
        // Call the Feature Service API
        String featureServiceUrl = "http://localhost:8082/feature/grades";
        List<Map<String, Object>> grades = restTemplate.getForObject(featureServiceUrl, List.class);

        // Add the grades to the model
        model.addAttribute("grades", grades);

        // Return the HTML template
        return "grades";
    }
}
