package com.example.viewservice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.HttpServerErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle when the database or feature node is not reachable
    @ExceptionHandler(ResourceAccessException.class)
    public String handleServiceUnavailable(ResourceAccessException ex, Model model) {
        model.addAttribute("errorMessage", "A service required to process your request is temporarily unavailable. Please try again later.");
        return "error"; // Render an error.html template
    }

    // Handle HTTP errors from the Feature/Database Node
    @ExceptionHandler(HttpServerErrorException.class)
    public String handleHttpServerError(HttpServerErrorException ex, Model model) {
        model.addAttribute("errorMessage", "An error occurred while processing your request. Please try again later.");
        return "error"; // Render an error.html template
    }

    // Handle other exceptions globally
    @ExceptionHandler(Exception.class)
    public String handleGenericError(Exception ex, Model model) {
        model.addAttribute("errorMessage", "An unexpected error occurred. Please contact support.");
        return "error"; // Render a generic error.html template
    }
}
