package com.example.featureservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@RestController
public class FeatureCourseController {

    private final RestTemplate restTemplate;

    @Value("${database.service.url}") // Database Node URL from application.properties
    private String databaseServiceUrl;

    public FeatureCourseController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/feature/courses")
    public ResponseEntity<?> getCourses() {
        try {
            // Call the Database Node to get courses
            String url = databaseServiceUrl + "/courses";
            Object courses = restTemplate.getForObject(url, Object.class);
            return ResponseEntity.ok(courses);

        } catch (ResourceAccessException ex) {
            // Handle case where the Database Node is unavailable
            return ResponseEntity
                    .status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("The Database Node is currently unavailable, but the Feature Node is operational.");
        } catch (Exception ex) {
            // Handle other exceptions
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred in the Feature Node.");
        }
    }
}
