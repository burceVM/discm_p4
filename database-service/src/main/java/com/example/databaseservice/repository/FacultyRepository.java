package com.example.databaseservice.repository;

import com.example.databaseservice.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}