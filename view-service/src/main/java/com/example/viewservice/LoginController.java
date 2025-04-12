package com.example.viewservice;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    JWTHandler handler = new JWTHandler();
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);



            if (username.equals("admin") && password.equals("admin")) {
                handler.returnJWTStringForFaculty(username);

                Cookie jwtCookie = new Cookie("token", handler.returnJWTStringForFaculty(username));
                jwtCookie.setHttpOnly(true); // Prevents JavaScript access
                jwtCookie.setSecure(true);   // Use HTTPS only
                jwtCookie.setPath("/");
                jwtCookie.setMaxAge(60 * 60); // 1 hour

                response.addCookie(jwtCookie);
            }
            else {
                handler.returnJWTStringForFaculty(username);

                Cookie jwtCookie = new Cookie("token", handler.returnJWTStringForStudent(username));
                jwtCookie.setHttpOnly(true); // Prevents JavaScript access
                jwtCookie.setSecure(true);   // Use HTTPS only
                jwtCookie.setPath("/");
                jwtCookie.setMaxAge(60 * 60); // 1 hour

                response.addCookie(jwtCookie);
            }
            return "redirect:/dashboard";
    }


}
