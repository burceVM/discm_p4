package com.example.featureservice.service;

import com.example.databaseservice.entity.Faculty;
import com.example.databaseservice.entity.Student;
import com.example.databaseservice.repository.FacultyRepository;
import com.example.databaseservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public String login(String username, String password) {
        // Step 1: Check for Student
        Optional<Student> student = studentRepository.findByUsername(username);
        if (student.isPresent()) {
            // TODO: Validate password (e.g., hashed comparison)
            return jwtTokenProvider.generateToken(username, "STUDENT");
        }

        // Step 2: Check for Faculty
        Optional<Faculty> faculty = facultyRepository.findByUsername(username);
        if (faculty.isPresent()) {
            // TODO: Validate password
            return jwtTokenProvider.generateToken(username, "FACULTY");
        }

        // Step 3: If no user is found, throw an exception
        throw new RuntimeException("Invalid username or password");
    }
}
