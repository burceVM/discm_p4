package com.example.viewservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Controller
public class ViewGradeController {

    private final RestTemplate restTemplate;

    @Value("${feature.service.url}") // Feature Node URL
    private String featureServiceUrl;

    public ViewGradeController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/grades")
    public String showGrades(Model model) {
        try {
            // Call the Feature Node to get grades
            String url = featureServiceUrl + "/feature/grades";
            Object response = restTemplate.getForObject(url, Object.class);
            model.addAttribute("grades", response);

            return "grades"; // Render grades.html

        } catch (HttpServerErrorException.ServiceUnavailable ex) {
            // Specific error message when the Database Node is down
            model.addAttribute("errorTitle", "Database Node Unavailable");
            model.addAttribute("errorDescription", "The Database Node is currently down. However, the Feature Node is operational. Please try again later.");
            return "error"; // Render error.html template

        } catch (ResourceAccessException ex) {
            // Error when the Feature Node is unreachable
            model.addAttribute("errorTitle", "Feature Node Unreachable");
            model.addAttribute("errorDescription", "The Feature Node seems to be unreachable. Please try again later.");
            return "error"; // Render error.html template

        } catch (Exception ex) {
            // Catch-all for unknown errors
            model.addAttribute("errorTitle", "Unexpected Error");
            model.addAttribute("errorDescription", "An unexpected error occurred. Please try again later.");
            return "error"; // Render error.html template
        }
    }
}
