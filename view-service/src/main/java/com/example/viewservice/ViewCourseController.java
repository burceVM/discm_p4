package com.example.viewservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Controller
public class ViewCourseController {

    private final RestTemplate restTemplate;

    @Value("${feature.service.url}") // Feature Node URL from application.properties
    private String featureServiceUrl;

    public ViewCourseController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/courses")
    public String showCourses(Model model) {
        try {
            // Call the Feature Node to get courses
            String url = featureServiceUrl + "/feature/courses";
            Object response = restTemplate.getForObject(url, Object.class);
            model.addAttribute("courses", response);

            return "courses"; // Render courses.html template

        } catch (HttpServerErrorException.ServiceUnavailable ex) {
            // Specific error message when the Database Node is down
            model.addAttribute("errorTitle", "Database Node Unavailable");
            model.addAttribute("errorDescription", "The Database Node is currently down. However, the Feature Node is operational. Please try again later.");
            return "error"; // Render error.html

        } catch (ResourceAccessException ex) {
            // Error when Feature Node is unreachable
            model.addAttribute("errorTitle", "Feature Node Unreachable");
            model.addAttribute("errorDescription", "The Feature Node seems to be unreachable. Please try again later.");
            return "error"; // Render error.html

        } catch (Exception ex) {
            // Catch-all for unknown errors
            model.addAttribute("errorTitle", "Unexpected Error");
            model.addAttribute("errorDescription", "An unexpected error occurred. Please try again later.");
            return "error"; // Render error.html
        }
    }
}
