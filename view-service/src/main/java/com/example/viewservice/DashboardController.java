package com.example.viewservice;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    JWTHandler jwtHandler = new JWTHandler();
    @GetMapping("/dashboard")
    public String dashboard(HttpServletRequest request, Model model) {
        if (jwtHandler.getCookie(request) != null) {
            model.addAttribute("role", jwtHandler.returnRole(request));
            return "dashboard";
        }
        else return "redirect:/";
    }

}
