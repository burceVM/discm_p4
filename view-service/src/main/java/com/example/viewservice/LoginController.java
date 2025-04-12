package com.example.viewservice;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    JWTHandler jwtHandler = new JWTHandler();
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);



            if (username.equals("admin") && password.equals("admin")) {
                jwtHandler.returnJWTStringForFaculty(username);

                Cookie jwtCookie = new Cookie("token", jwtHandler.returnJWTStringForFaculty(username));
                jwtCookie.setHttpOnly(true); // Prevents JavaScript access
                jwtCookie.setPath("/");
                jwtCookie.setMaxAge(60 * 60); // 1 hour

                response.addCookie(jwtCookie);
            }
            else {
                jwtHandler.returnJWTStringForFaculty(username);

                Cookie jwtCookie = new Cookie("token", jwtHandler.returnJWTStringForStudent(username));
                jwtCookie.setHttpOnly(true); // Prevents JavaScript access
                jwtCookie.setPath("/");
                jwtCookie.setMaxAge(60 * 60); // 1 hour

                response.addCookie(jwtCookie);
            }
            return "redirect:/dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        // Invalidate the token cookie
        Cookie cookie = new Cookie("token", "");
        cookie.setPath("/");         // Same path as the original cookie
        cookie.setHttpOnly(true);    // Keep the same settings
        cookie.setMaxAge(0);         // Deletes the cookie
        response.addCookie(cookie);

        return "redirect:/";    // Or wherever you want to go
    }

    @GetMapping()
    public String index(HttpServletRequest request) {
        if (jwtHandler.getCookie(request) == null)
            return "index";
        else
            return "redirect:/dashboard";
    }
}
